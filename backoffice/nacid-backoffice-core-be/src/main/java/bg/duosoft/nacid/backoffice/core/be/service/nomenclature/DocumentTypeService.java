package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.SqlRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.DocumentTypeRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.DocumentTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.Direction;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentTypeMapper;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshareddata.util.bool.BooleanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 18.08.2022
 * Time: 16:58
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DocumentTypeService extends NomenclatureServiceBase<Integer, DocumentTypeDTO, DocumentTypeFilterDTO> {
    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;
    private final DocumentTypeValidator documentTypeValidator;
    private final IntegerToBooleanMapper integerToBooleanMapper;
    private final ApplicationsService applicationsService;
    private final SqlRepository sqlRepository;


    @Override
    protected DocumentTypeRepository getNomenclaturesRepository() {
        return documentTypeRepository;
    }

    @Override
    protected DocumentTypeMapper getNomenclaturesMapper() {
        return documentTypeMapper;
    }

    @Override
    protected BaseNomenclatureValidator<Integer, DocumentTypeDTO, DocumentTypeFilterDTO> getValidator() {
        return documentTypeValidator;
    }

    @Cacheable(value = "DocumentTypeService", key = "'document-types-' +  #applicationType + '-' + #onlyActive")
    public List<DocumentTypeDTO> selectByApplicationType(String applicationType, boolean onlyActive) {
        List<DocumentTypeEntity> documentTypes = documentTypeRepository.findByApplicationType(applicationType, onlyActive);
        return documentTypeMapper.toDtoList(documentTypes);
    }

    /**
     *
     * @param docTypeId
     * @param applicationId
     * @return null ako tipa dokument ne pasva na dadeniq application (primerno statusa na application-a ne e podhodqsht za generirane na tozi tip document) ili nqma detaili koito da match-vat kriteriite, svyrzani s dadeniq application
     */
    public DocumentTypeDTO selectDocumentType(Integer docTypeId, DocCategory docCategory, Integer applicationId) {
        DocumentTypeDTO dto = selectById(docTypeId);
        Pair<String, String> ateAseCodes = applicationsService.getAppTypeAndSubtypeById(applicationId);
        String statusCode = applicationsService.selectStatusCodeById(applicationId);
        return filterDocumentType(applicationId, docCategory, ateAseCodes, dto, null, statusCode, null).orElse(null);
    }


    public List<DocumentTypeDTO> selectDocumentTypes(Integer selectedDocumentTypeId, Integer applicationId, DocCategory docCategory, Direction direction, Boolean isActive, Boolean finalized) {

        Pair<String, String> ateAseCodes = applicationsService.getAppTypeAndSubtypeById(applicationId);
        String statusCode = applicationsService.selectStatusCodeById(applicationId);
        Integer active = integerToBooleanMapper.booleanToInt(isActive);

        List<DocumentTypeEntity> recs = documentTypeRepository.selectDocumentTypes(docCategory.code(), active, Objects.nonNull(direction) ? direction.code() : null, ateAseCodes.getFirst(), ateAseCodes.getSecond());

        return documentTypeMapper.toDtoList(recs)
                .stream()
                .map(d -> filterDocumentType(applicationId, docCategory, ateAseCodes, d, selectedDocumentTypeId, statusCode, finalized))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    private Optional<DocumentTypeDTO> filterDocumentType(Integer applicationId, DocCategory docCategory, Pair<String, String> ateAseCodes, DocumentTypeDTO dto, Integer selectedDocumentTypeId, String statusCode, Boolean finalized) {
        /**
         * documentTypeRepository.selectDocumentTypes selektira po applicationtype / subtype, no ne syvsem - toi selektira document types, koito imat detail machvasht ate/ase, no mu vry6ta vsichki detaili, t.e. i tezi koito ne sa za konkretniq ate / ase!!!
         * Primer - dokumenta reshenie za otkaz e konfiguriran i za rudi i za regprof, ako ate = AR, ase = UDI, to tozi dokument shte se vyrne ot documentTypeRepository.selectDocumentTypes, no shte mu se vyrnat vsichki detajli, t.e. i tezi koito se otnasqt za regprof
         * zatova se nalaga dopylnitelno razkarvane na tezi detajli, koito ne syotvetstvat na konkretniq applicationType / subtype!!!!
         */
        removeDetailsNotMatchingApplicationTypeSubtype(ateAseCodes.getFirst(), ateAseCodes.getSecond(), dto);
        removeDetailsNotMatchingDocCategory(docCategory, dto);//sy6toto kato s removeDetailsNotMatchingApplicationTypeSubtype
        removeDetailsNotMatchingCondition(applicationId, dto);
        if (createDocumentTypeFilter(selectedDocumentTypeId, docCategory, statusCode, finalized).test(dto)) {
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    private Predicate<DocumentTypeDTO> createDocumentTypeFilter(Integer selectedDocumentTypeId, DocCategory docCategory, String statusCode, Boolean finalized) {
        Predicate<DocumentTypeDTO> and = (d) -> filterDocumentTypeByFinalizedCondition(d, finalized);
        and = and.and(d -> filterDocumentTypesByStatus(d, docCategory, statusCode));
        and = and.and(d -> !ObjectUtils.isEmpty(d.getDetails()));
        Predicate<DocumentTypeDTO> predicate = d -> Objects.equals(selectedDocumentTypeId, d.getId());
        return predicate.or(and);
    }

    private DocumentTypeDTO removeDetailsNotMatchingCondition(Integer applicationId, DocumentTypeDTO dte) {
        dte.setDetails(dte.getDetails().stream().filter(d -> isConditionMatched(applicationId, d.getCondition())).collect(Collectors.toList()));
        return dte;
    }

    private DocumentTypeDTO removeDetailsNotMatchingDocCategory(DocCategory category, DocumentTypeDTO dte) {
        dte.setDetails(dte.getDetails().stream().filter(d -> d.getDocumentCategory().getId().equals(category.code())).collect(Collectors.toList()));
        return dte;
    }

    private DocumentTypeDTO removeDetailsNotMatchingApplicationTypeSubtype(String applicationType, String applicationSubtype, DocumentTypeDTO dte) {
        List<DocumentTypeDetailDTO> details = dte
                .getDetails()
                .stream()
                .filter(d -> d.getApplicationType() == null || d.getApplicationType().getId().equals(applicationType))
                .filter(d -> d.getApplicationSubtype() == null || d.getApplicationSubtype().getId().equals(applicationSubtype))
                .collect(Collectors.toList());
        dte.setDetails(details);
        return dte;
    }
    private Boolean filterDocumentTypesByStatus(DocumentTypeDTO documentType, DocCategory docCategory, String statusCode) {
        return !docCategory.code().equals(DocCategory.APP_ATTACHMENTS.code()) || ObjectUtils.isEmpty(documentType.getStatuses()) || documentType.getStatuses().stream().map(sts -> sts.getStatus().getId()).anyMatch(s -> Objects.equals(s, statusCode));
    }

    public Boolean filterDocumentTypeByFinalizedCondition(DocumentTypeDTO dte, Boolean finalized) {
        if (Objects.nonNull(finalized)) {
            List<DocumentTypeDetailDTO> details = dte.getDetails();
            if (!finalized && CollectionUtils.isEmpty(details)) {
                return true;
            }
            if (!CollectionUtils.isEmpty(details)) {
                DocumentTypeDetailDTO detail = details.stream().filter(r -> Objects.nonNull(r.getFinalizationType())).findFirst().orElse(null);
                return (finalized && Objects.nonNull(detail)) || (!finalized && Objects.isNull(detail));
            } else {
                return false;
            }
        }
        return true;
    }


    private boolean isConditionMatched(Integer applicationId, String condition) {
        if (ObjectUtils.isEmpty(condition)) {
            return true;
        }
        List result = sqlRepository.selectRowsAsObjectArray(condition, Map.of("applicationId", applicationId));
        if (ObjectUtils.isEmpty(result)) {
            return false;
        }
        Object res = result.get(0);
        if (res == null) {
            return false;
        } else if (res instanceof Object[] o) {
            return o[0] instanceof Boolean b ? b : BooleanUtils.parseBoolean(o[0].toString(), false);
        } else if (res instanceof Boolean b) {
            return b;
        } else {
            throw new RuntimeException("Unknown type for res " + res.getClass());
        }
    }

}
