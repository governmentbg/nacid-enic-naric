package bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.AttachmentMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.*;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReferenceDataMapper.class, CommissionApplicationMapper.class, CommissionParticipationMapper.class, AttachmentMapper.class})
public abstract class CommissionCalendarMapper extends BaseObjectMapper<CommissionCalendarEntity, CommissionCalendarDTO> {
    @AfterMapping
    protected void afterToEntity(CommissionCalendarDTO source, @MappingTarget CommissionCalendarEntity target) {
        List<CommissionApplicationEntity> applications = target.getApplications();
        List<CommissionParticipationEntity> participations = target.getParticipations();
        if (!CollectionUtils.isEmpty(applications)) {
            for (CommissionApplicationEntity application : applications) {
                application.setCalendarId(target.getId());
            }
        }

        if (!CollectionUtils.isEmpty(participations)) {
            for (CommissionParticipationEntity participation : participations) {
                participation.setCalendar(target);
            }
        }

    }
}
