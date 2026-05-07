package bg.duosoft.nacid.backoffice.core.data.mapper.fo.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.AttachedDocumentDTO;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {FoReferenceDataMapper.class, FoDocTypeMapper.class})
public abstract class FoAttachedDocMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "copyType", source = "attachmentForm")
    @Mapping(target = "documentType", source = "attachmentType")
    public abstract AttachedDocDTO toBackofficeObject(AttachedDocumentDTO source);

    @AfterMapping
    public void afterToBackofficeObject(AttachedDocumentDTO source, @MappingTarget AttachedDocDTO target) {
        target.setDocCategory(new ReferenceDataDTO(ReferenceDataDomain.DOC_CATEGORY.domain(), DocCategory.APP_ATTACHMENTS.code()));
    }

}
