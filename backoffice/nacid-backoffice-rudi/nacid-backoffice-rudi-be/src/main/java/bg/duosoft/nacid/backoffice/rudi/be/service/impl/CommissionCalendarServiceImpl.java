package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.calendar.CalendarProtocolsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionCalendarFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.AttachmentMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionCalendarEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.CalendarProcessDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.CommissionCalendarMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.VCommissionCalendarMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.*;
import bg.duosoft.nacid.backoffice.rudi.be.service.*;
import bg.duosoft.nacid.backoffice.rudi.be.validator.CommissionCalendarProcessDataValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.CommissionCalendarValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CommissionCalendarServiceImpl extends CrudServiceBaseImpl<Integer, CommissionCalendarDTO> implements CommissionCalendarService {
    private final CommissionCalendarMapper commissionCalendarMapper;
    private final VCommissionCalendarMapper vcommissionCalendarMapper;
    private final CommissionCalendarRepository commissionCalendarRepository;
    private final CommissionCalendarValidator validator;
    private final CalendarProcessDataMapper calendarProcessDataMapper;
    private final CommissionCalendarProcessDataValidator commissionCalendarProcessDataValidator;
    private final RudiApplicationService rudiApplicationService;
    private final AttachmentMapper attachmentMapper;
    private final ApplicationsService vwApplicationsService;
    private final CommissionMemberService commissionMemberService;
    private final RudiStatusService rudiStatusService;


    @Override
    protected CommissionCalendarRepository getRepository() {
        return commissionCalendarRepository;
    }

    @Override
    protected CommissionCalendarMapper getMapper() {
        return commissionCalendarMapper;
    }

    @Override
    protected Validator getValidator() {
        return validator;
    }


    @Override
    public CommissionCalendarDTO create(CommissionCalendarDTO calendar) {
        CommissionCalendarDTO commissionCalendarDTO = super.create(calendar);
        addApplicationsAfterCreate(commissionCalendarDTO);
        addMembersAfterCreate(commissionCalendarDTO);
        update(commissionCalendarDTO);
        return commissionCalendarDTO;
    }


    private void addMembersAfterCreate(CommissionCalendarDTO calendar) {
        List<CommissionMemberDTO> commissionMembers = commissionMemberService.selectMembersByPosition(ReferenceDataCode.COMMISSION_PARTICIPATION_MEMBER.code());
        if (!CollectionUtils.isEmpty(commissionMembers)) {
            calendar.setParticipations(new ArrayList<>());
            commissionMembers.forEach(r -> {
                if (r.getIsActive()) {
                    calendar.getParticipations().add(new CommissionParticipationDTO(calendar.getId(), r, true, true, false));
                }
            });
        }
    }

    private void addApplicationsAfterCreate(CommissionCalendarDTO calendar) {
        List<RudiApplicationsDTO> rudiApplicationsDTOS = vwApplicationsService.selectAllByTypeAndStatus(ApplicationType.RUDI.code(), ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION.appSubType(), ApplicationStatusType.AUTHENTIC.code());
        if (!CollectionUtils.isEmpty(rudiApplicationsDTOS)) {
            calendar.setApplications(new ArrayList<>());
            rudiApplicationsDTOS.forEach(application -> {
                CommissionApplicationDTO applicationDTO = new CommissionApplicationDTO();
                applicationDTO.setApplicationId(application.getId());
                applicationDTO.setCalendarId(calendar.getId());
                calendar.getApplications().add(applicationDTO);
            });
        }
    }

    @Override
    public Integer getMaxSessionNum() {
        return commissionCalendarRepository.getMaxSessionNum() + 1;
    }

    @Override
    public List<VCommissionCalendarDTO> searchRecords(CommissionCalendarFilterDTO filter) {
        return vcommissionCalendarMapper.toDtoList(commissionCalendarRepository.searchRecords(filter));
    }

    @Override
    public String getSecretary(Integer calendarId) {
        return commissionCalendarRepository.getSecretary(calendarId);
    }

    @Override
    public int getRecordsCount(CommissionCalendarFilterDTO filter) {
        return commissionCalendarRepository.getRecordsCount(filter);
    }

    @Override
    public boolean existsById(Integer id) {
        if (Objects.isNull(id)) {
            return false;
        }

        return commissionCalendarRepository.existsById(id);
    }

    @Override
    public String getFullNumber(Integer id) {
        CommissionCalendarDTO commissionCalendarDTO = selectById(id);
        Date sessionTimeAsDate = DateUtils.convertToDate(commissionCalendarDTO.getSessionTime());
        return commissionCalendarDTO.getSessionNum().toString().concat("/").concat(DateUtils.formatDate(sessionTimeAsDate));
    }

    @Override
    public CalendarProcessDataDTO getProcessData(Integer calendarId, Integer applicationId) {
        return calendarProcessDataMapper.toDto(commissionCalendarRepository.getProcessData(calendarId, applicationId));
    }

    @Override
    public void saveProcessData(CalendarProcessDataDTO processData) {
        if (Objects.isNull(processData) || Objects.isNull(processData.getCalendarId()) || Objects.isNull(processData.getApplicationId())) {
            throw new RuntimeException("Invalid data on commission calendar process data save!");
        }
        BadRequestValidator.validateRequest(commissionCalendarProcessDataValidator, processData, new Object[]{false, this});
        Integer applicationId = processData.getApplicationId();
        Integer calendarId = processData.getCalendarId();
        RudiApplicationDTO rudiApplication = rudiApplicationService.selectById(applicationId);

        if (Objects.isNull(rudiApplication)) {
            throw new RuntimeException("Application not found on commission calendar process data save!");
        }

        //Fill application info and motives
        CommissionApplicationDTO commissionApplication = rudiApplication.getCommissionApplications().stream().filter(r ->
                r.getCalendarId().equals(calendarId) && r.getApplicationId().equals(applicationId)
        ).findFirst().orElse(null);
        commissionApplication.setMotives(processData.getMotives());
        commissionApplication.setApplicantInfo(processData.getApplicantInfo());


        //Fill application specialities
        List<ApplicationRecognizedSpecialityDTO> recognizedSpecialities = new ArrayList<>();
        if (CollectionUtils.isEmpty(rudiApplication.getRecognizedSpecialities())) {
            rudiApplication.setRecognizedSpecialities(new ArrayList<>());
        }

        if (!CollectionUtils.isEmpty(processData.getSpecialities())) {
            processData.getSpecialities().stream().forEach(speciality -> {
                ApplicationRecognizedSpecialityDTO specialityDTO = rudiApplication.getRecognizedSpecialities().stream().filter(r -> r.getSpeciality().equals(speciality)).findFirst().orElse(null);
                if (Objects.isNull(specialityDTO)) {
                    specialityDTO = new ApplicationRecognizedSpecialityDTO(null, applicationId, speciality);
                }
                recognizedSpecialities.add(specialityDTO);
            });
        }
        rudiApplication.setRecognizedSpecialities(recognizedSpecialities);

        //Fill application recognized details
        if (Objects.isNull(rudiApplication.getApplicationRecognizedDetails())) {
            rudiApplication.setApplicationRecognizedDetails(new ApplicationRecognizedDetailsDTO());
            rudiApplication.getApplicationRecognizedDetails().setApplicationId(applicationId);
        }

        ApplicationRecognizedDetailsDTO recognizedDetails = rudiApplication.getApplicationRecognizedDetails();
        recognizedDetails.setRecognizedEduLevel(processData.getRecognizedEduLevel());
        recognizedDetails.setRecognizedQualification(processData.getRecognizedQualification());
        if (Objects.nonNull(processData.getRecognizedProfGroupId())) {
            ProfGroupDTO profGroup = new ProfGroupDTO();
            profGroup.setId(processData.getRecognizedProfGroupId());
            recognizedDetails.setProfGroup(profGroup);
        }
        //Save legalReason
        if (Objects.isNull(processData.getLegalReasonId())) {
            rudiApplication.setLegalReason(null);
        } else {
            rudiApplication.setLegalReason(new LegalReasonDTO(processData.getLegalReasonId()));
        }
        rudiApplicationService.save(rudiApplication, ValidationScope.COMMISSION_CALENDAR_PROCESS_DATA);
        rudiStatusService.insertRudiStatus(applicationId, InsertStatusDTO.builder().applicationId(applicationId).calendarId(calendarId).statusId(processData.getStatusCode()).legalReasonId(processData.getLegalReasonId()).build());
    }

    @Override
    public Integer selectLastCommissionSessionNumByApnId(Integer applicationId) {
        return commissionCalendarRepository.selectLastCommissionSessionNumByApnId(applicationId);
    }

    @Override
    public AttachmentDTO getCalendarProtocol(Integer calendarId) {
        return attachmentMapper.toDto(commissionCalendarRepository.getCommissionCalendarProtocol(calendarId));
    }

    @Override
    public CalendarProtocolsDTO getCalendarProtocols(Integer calendarId) {
        CommissionCalendarEntity commissionCalendarEntity = commissionCalendarRepository.findById(calendarId).orElse(null);

        if (Objects.isNull(commissionCalendarEntity)) {
            throw new ResourceNotFoundException();
        }

        CalendarProtocolsDTO protocols = new CalendarProtocolsDTO();
        protocols.setCommissionProtocol(attachmentMapper.toDto(commissionCalendarEntity.getCommissionProtocol()));
        protocols.setScannedCommissionProtocol(attachmentMapper.toDto(commissionCalendarEntity.getScannedCommissionProtocol()));
        return protocols;
    }

    @Override
    public AttachmentDTO updateProtocol(Integer calendarId, AttachmentDTO protocol) {
        CommissionCalendarEntity commissionCalendarEntity = commissionCalendarRepository.findById(calendarId).orElse(null);
        if (Objects.isNull(commissionCalendarEntity)) {
            throw new ResourceNotFoundException();
        }
        CommissionCalendarDTO calendarDTO = commissionCalendarMapper.toDto(commissionCalendarEntity);
        calendarDTO.setCommissionProtocol(protocol);
        CommissionCalendarDTO updatedCalendar = update(calendarDTO);
        return updatedCalendar.getCommissionProtocol();
    }

    @Override
    public CalendarProtocolsDTO updateProtocols(Integer calendarId, CalendarProtocolsDTO protocols) {
        CommissionCalendarEntity commissionCalendarEntity = commissionCalendarRepository.findById(calendarId).orElse(null);
        if (Objects.isNull(commissionCalendarEntity)) {
            throw new ResourceNotFoundException();
        }
        CommissionCalendarDTO calendarDTO = commissionCalendarMapper.toDto(commissionCalendarEntity);
        calendarDTO.setCommissionProtocol(protocols.getCommissionProtocol());
        calendarDTO.setScannedCommissionProtocol(protocols.getScannedCommissionProtocol());
        CommissionCalendarDTO updatedCalendar = update(calendarDTO);
        return new CalendarProtocolsDTO(updatedCalendar.getCommissionProtocol(), updatedCalendar.getScannedCommissionProtocol(), null, null);
    }

    @Override
    protected void beforeCreateOrUpdate(CommissionCalendarDTO dto) {
        if (Objects.isNull(dto.getId())) {
            dto.setDateCreated(LocalDateTime.now());
            dto.setUserCreated(SecurityUtils.getUsername());
        }
    }
}
