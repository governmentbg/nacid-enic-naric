package bg.duosoft.nacid.backoffice.core.data.mapper.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAdditionalAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAdditionalSubmissionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationAdditionalSubmissionDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        ApplicationAdditionalAttachedDocMapper.class
})
public abstract class ApplicationAdditionalSubmissionMapper extends BaseObjectMapper<ApplicationAdditionalSubmissionEntity, ApplicationAdditionalSubmissionDTO> {

    @AfterMapping
    public void afterToEntity(ApplicationAdditionalSubmissionDTO source, @MappingTarget ApplicationAdditionalSubmissionEntity target) {
        Integer id = target.getId();
        if (Objects.isNull(id)) {
            target.setBoUserAccepted(SecurityUtils.getUsername());
            target.setBoDateTransferred(LocalDateTime.now());
        }

        List<ApplicationAdditionalAttachedDocEntity> attachedDocs = target.getAttachedDocs();
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            attachedDocs.forEach(a -> a.setSubmission(target));
        }

        if (Objects.isNull(target.getApplication())) {
            target.setApplication(new ApplicationEntity());
        }
        
        target.getApplication().setId(source.getApplicationId());
    }
}
