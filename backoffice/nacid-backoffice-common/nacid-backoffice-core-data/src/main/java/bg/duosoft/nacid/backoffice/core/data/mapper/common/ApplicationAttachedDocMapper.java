package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsUrlBuilder;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocAttachmentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", uses = {DocumentTypeMapper.class, ReferenceDataMapper.class, AttachmentMapper.class, ApplicationAttachedDocAttachmentMapper.class, ApplicationCertificatesMapper.class})
public abstract class ApplicationAttachedDocMapper extends BaseObjectMapper<ApplicationAttachedDocEntity, AttachedDocDTO> {

    @Autowired
    private AbdocsUrlBuilder abdocsUrlBuilder;

    @AfterMapping
    public void afterToDto(ApplicationAttachedDocEntity source, @MappingTarget AttachedDocDTO target) {
        String docflowId = target.getDocflowId();
        if (StringUtils.hasText(docflowId)) {
            try {
                target.setAbdocsViewDocumentUrl(abdocsUrlBuilder.viewDocWithAuth(Integer.valueOf(docflowId)));
            } catch (Exception e) {
                log.error("Cannot set abdocs view document url for attachment with docflow id = " + docflowId);
            }
        }
    }

    @AfterMapping
    public void afterToEntity(AttachedDocDTO source, @MappingTarget ApplicationAttachedDocEntity target) {
        ReferenceDataUtils.setDefaultDomain(target.getCopyType(), ReferenceDataDomain.COPY_TYPE);
        ReferenceDataUtils.setDefaultDomain(target.getDocCategory(), ReferenceDataDomain.DOC_CATEGORY);

        List<ApplicationAttachedDocAttachmentEntity> attachedDocAttachments = target.getAttachedDocAttachments();
        if (!CollectionUtils.isEmpty(attachedDocAttachments)) {
            attachedDocAttachments.forEach(a -> a.setAttachedDoc(target));
        } else {
            target.setAttachedDocAttachments(null);
        }
    }

}
