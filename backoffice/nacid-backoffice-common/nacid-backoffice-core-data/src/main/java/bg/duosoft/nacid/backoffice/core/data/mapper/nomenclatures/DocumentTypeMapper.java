package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToAbdocsConfigEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToDocCategoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:32
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, DocumentTypeDetailsMapper.class, CfgDocTypeToAppStatusMapper.class, DocumentTypeAbdocsConfigMapper.class})
public abstract class DocumentTypeMapper extends BaseNomenclatureMapper<DocumentTypeEntity, DocumentTypeDTO> {

    @AfterMapping
    protected void afterMapping(DocumentTypeDTO source, @MappingTarget DocumentTypeEntity target) {
        List<CfgDocTypeToDocCategoryEntity> details = target.getDetails();
        if (!CollectionUtils.isEmpty(details)) {
            for (CfgDocTypeToDocCategoryEntity detail : details) {
                if (Objects.isNull(detail.getDocumentCategory()) || Objects.isNull(detail.getDocumentCategory().getPk())) {
                    throw new RuntimeException("Empty category!");
                }
                detail.setDocumentType(target);
                detail.getDocumentCategory().getPk().setDomain(ReferenceDataDomain.DOC_CATEGORY.domain());
                if (Objects.nonNull(detail.getFinalizationType())) {
                    detail.getFinalizationType().getPk().setDomain(ReferenceDataDomain.DOCUMENT_FINALIZATION_TYPE.domain());
                }
                if (Objects.nonNull(detail.getDefaultAttachmentVisibility())) {
                    detail.getDefaultAttachmentVisibility().getPk().setDomain(ReferenceDataDomain.ATTACHMENT_VISIBILITY.domain());
                }
            }
        }
        List<CfgDocTypeToAppStatusEntity> statuses = target.getStatuses();
        if (statuses != null) {
            statuses.forEach(s -> s.setDocumentType(target));
        }

        List<CfgDocTypeToAbdocsConfigEntity> abdocsConfigs = target.getAbdocsConfigs();
        if (!CollectionUtils.isEmpty(abdocsConfigs)) {
            abdocsConfigs.forEach(s -> s.setDocumentType(target));
        }
    }
}
