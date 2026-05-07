package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocAttachmentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Mapper(componentModel = "spring", uses = {ReferenceDataMapper.class, AttachmentMapper.class})
public abstract class ApplicationAttachedDocAttachmentMapper extends BaseObjectMapper<ApplicationAttachedDocAttachmentEntity, AttachedDocAttachmentDTO> {

    @AfterMapping
    public void afterToEntity(AttachedDocAttachmentDTO source, @MappingTarget ApplicationAttachedDocAttachmentEntity target) {
        ReferenceDataUtils.setDefaultDomain(target.getAttachmentVisibility(), ReferenceDataDomain.ATTACHMENT_VISIBILITY);
        removeAttachmentIfFilenameIsEmpty(target);
    }

    //TODO I'm not sure if that is necessary, but it was added for the scanned files in old implementation before rewriting.
    private static void removeAttachmentIfFilenameIsEmpty(ApplicationAttachedDocAttachmentEntity target) {
        if (Objects.isNull(target.getAttachment()) || !StringUtils.hasText(target.getAttachment().getFileName())) {
            target.setAttachment(null);
        }
    }

}
