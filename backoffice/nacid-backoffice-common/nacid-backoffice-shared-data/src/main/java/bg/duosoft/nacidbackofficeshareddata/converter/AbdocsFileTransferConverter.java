package bg.duosoft.nacidbackofficeshareddata.converter;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsAdminClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.Direction;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureSystem;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAbdocsConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsConfigUtils;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsConverter;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacidbackofficeshareddata.exception.DocCreationConverterException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AbdocsFileTransferConverter {

    private final AbdocsAdminClient abdocsAdminClient;


    public DocCreation convertFileDocumentObject(ApplicationDTO applicationDTO, AttachedDocDTO attachedDocDTO, List<ExternalNomenclaturesMapDTO> abdocsSettlements) {
        return convertFileDocumentObject(applicationDTO, attachedDocDTO.getDocumentType(), abdocsSettlements);
    }

    public DocCreation convertFileDocumentObject(ApplicationDTO applicationDTO, DocumentTypeDTO documentType, List<ExternalNomenclaturesMapDTO> abdocsSettlements) {
        try {
            DocumentTypeAbdocsConfigDTO abdocsConfig = documentType.getSingleAbdocsConfigOrThrowException(applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId());
            DocumentType abdocsDocumentType = abdocsAdminClient.getDocumentTypeById(abdocsConfig.getAbdocsDocTypeId());
            if (Objects.isNull(abdocsDocumentType)) {
                throw new RuntimeException("Cannot transfer file because abdocs document type object is empty! DOC TYPE: " + documentType.getId());
            }

            DocCreation docCreation = new DocCreation();

            Direction direction = Direction.selectByCode(documentType.getDirection());
            if (direction == Direction.Input) {
                setInputDocumentData(docCreation);
            } else {
                setOutputDocumentData(documentType, docCreation, applicationDTO);
            }

            docCreation.setDocCaseLink(new DocCaseLinkDO());
            docCreation.setDocSourceTypeId(DocSourceType.Counter.value());
            docCreation.setParentDocId(selectParentDocumentId(applicationDTO));
            docCreation.setReceivedOriginalState(ReceivedOriginalState.ReceivedOriginal);
            docCreation.setDocSubject(documentType.getName());
            docCreation.setDocTypeId(abdocsConfig.getAbdocsDocTypeId());
            docCreation.setCorrespondents(AbdocsConverter.createDocCorrespondents(applicationDTO, abdocsSettlements));
            return docCreation;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DocCreationConverterException(e.getMessage(), e);
        }
    }
    private void setInputDocumentData(DocCreation docCreation) {
        docCreation.setDocDirection(DocDirection.Incoming);
        docCreation.setRegistration(new RegistrationDto());
        docCreation.getRegistration().setDocRegistrationType(DocRegistrationType.ByParentDocRegistrationNumber);
        docCreation.setDocStatusProcessed(true);
        docCreation.setAdditionalDocUnits(getInputDocumentAdditionalUnits());
    }

    private void setOutputDocumentData(DocumentTypeDTO documentType, DocCreation docCreation, ApplicationDTO applicationDTO) {
        docCreation.setDocDirection(DocDirection.Outgoing);

        boolean shouldCreateDocAction = AbdocsConfigUtils.shouldCreateDocAction(documentType, applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId());
        if (shouldCreateDocAction) {
            docCreation.setDocStatusProcessed(true);
            docCreation.setRegistration(new RegistrationDto());
            docCreation.getRegistration().setDocRegistrationType(DocRegistrationType.ByParentDocRegistrationNumber);
        } else {
            docCreation.setRegistration(null);
            docCreation.setDocStatusProcessed(AbdocsConfigUtils.shouldCreateDocAction(documentType, applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId()));
        }

        docCreation.setAdditionalDocUnits(getOutputDocumentAdditionalUnits(documentType, applicationDTO));
    }

    private List<AdditionalDocUnit> getInputDocumentAdditionalUnits() {
        AdditionalDocUnit fromAdditionalDocUnit = new AdditionalDocUnit();
        fromAdditionalDocUnit.setUsername(SecurityUtils.getUsername());
        fromAdditionalDocUnit.setRole(DocUnitRole.From);
        return Collections.singletonList(fromAdditionalDocUnit);
    }

    private List<AdditionalDocUnit> getOutputDocumentAdditionalUnits(DocumentTypeDTO documentType, ApplicationDTO applicationDTO) {
        List<AdditionalDocUnit> units = new ArrayList<>();
        DocumentTypeAbdocsConfigDTO abdocsConfig = documentType.getSingleAbdocsConfigOrThrowException(applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId());
        if (StringUtils.hasText(abdocsConfig.getAbdocsDocFrom())) {
            AdditionalDocUnit fromUnit = new AdditionalDocUnit();
            fromUnit.setUsername(abdocsConfig.getAbdocsDocFrom());
            fromUnit.setRole(DocUnitRole.From);
            units.add(fromUnit);
        }

        if (StringUtils.hasText(abdocsConfig.getAbdocsDocEditor())) {
            AdditionalDocUnit editorUnit = new AdditionalDocUnit();
            editorUnit.setUsername(abdocsConfig.getAbdocsDocEditor());
            editorUnit.setRole(DocUnitRole.Editors);
            units.add(editorUnit);
        }

        return CollectionUtils.isEmpty(units) ? null : units;
    }

    private Integer selectParentDocumentId(ApplicationDTO applicationDTO) {
        String abdocsRegNumber = AbdocsNumbersUtils.buildRegistrationNumber(applicationDTO.getEntryNumber(), applicationDTO.getEntryDate());
        Integer documentId = abdocsAdminClient.getDocumentIdByRegNumber(abdocsRegNumber);
        if (Objects.isNull(documentId)) {
            throw new RuntimeException("Cannot transfer document in ABDOCS, because parent document id is empty ! Abdocs reg number:  " + abdocsRegNumber);
        }

        return documentId;
    }
}
