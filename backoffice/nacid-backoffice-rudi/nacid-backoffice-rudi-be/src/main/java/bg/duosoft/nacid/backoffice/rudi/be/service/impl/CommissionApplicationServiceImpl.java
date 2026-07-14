package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarApplicationSaveDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.CommissionApplicationsMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.CommissionApplicationMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CommissionApplicationRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CommissionApplicationServiceImpl extends CrudServiceBaseImpl<Integer, CommissionApplicationDTO> implements CommissionApplicationService {
    private final CommissionApplicationMapper mapper;
    private final CommissionApplicationRepository commissionApplicationRepository;
    private final CommissionCalendarService commissionCalendarService;
    private final CommissionApplicationsMapper commissionApplicationsMapper;

    @Override
    protected CommissionApplicationRepository getRepository() {
        return commissionApplicationRepository;
    }

    @Override
    protected CommissionApplicationMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator getValidator() {
        return null;
    }

    @Override
    public void saveApplications(CommissionCalendarApplicationSaveDTO dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getCalendarId())) {
            throw new RuntimeException("Incorrect dto data on save commission applications!");
        }
        Integer calendarId = dto.getCalendarId();
        CommissionCalendarDTO existedCalendar = commissionCalendarService.selectById(calendarId);
        List<CommissionApplicationDTO> applications = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dto.getApplicationIds())) {
            dto.getApplicationIds().stream().forEach(applicationId -> {
                CommissionApplicationDTO commissionApplicationDTO = selectByCalendarAndApplicationId(calendarId, applicationId);
                if (Objects.isNull(commissionApplicationDTO)) {
                    commissionApplicationDTO = new CommissionApplicationDTO();
                    commissionApplicationDTO.setApplicationId(applicationId);
                }
                applications.add(commissionApplicationDTO);
            });
        }
        existedCalendar.setApplications(applications);
        commissionCalendarService.update(existedCalendar);
    }

    @Override
    public CommissionApplicationDTO selectByCalendarAndApplicationId(Integer calendarId, Integer applicationId) {
        CommissionApplicationEntity application = commissionApplicationRepository.selectByCalendarAndApplicationId(calendarId, applicationId);
        return mapper.toDto(application);
    }

    @Override
    public List<CommissionApplicationDTO> selectByApplicationId(Integer applicationId) {
        return mapper.toDtoList(commissionApplicationRepository.selectByApplicationId(applicationId));
    }

    @Override
    public List<CommissionApplicationDTO> selectByCalendarId(Integer calendarId) {
        return mapper.toDtoList(commissionApplicationRepository.selectByCalendarId(calendarId));
    }

    @Override
    public List<RudiCommissionApplicationsDTO> selectApplicationsByCalendarAndAppId(List<Integer> ids, Integer calendarId, String sortColumn, Boolean ascOrder) {
        return commissionApplicationsMapper.toDtoList(commissionApplicationRepository.selectApplicationsByCalendarAndAppId(ids, calendarId, sortColumn, ascOrder));
    }

    @Override
    public void updateCommissionApplicationAttachedDoc(Integer calendarId, Integer applicationId, AttachedDocDTO attachedDoc) {
        CommissionCalendarDTO existedCalendar = commissionCalendarService.selectById(calendarId);
        if (Objects.isNull(existedCalendar)){
            throw new RuntimeException("Calendar not found!");
        }
        CommissionApplicationDTO applicationDTO = existedCalendar.getApplications().stream().filter(r -> r.getApplicationId().equals(applicationId)).findFirst().orElse(null);
        if (Objects.isNull(applicationDTO)){
            throw new RuntimeException("Application not found!");
        }
        applicationDTO.setAttachedDoc(attachedDoc);
        commissionCalendarService.update(existedCalendar);
    }
}
