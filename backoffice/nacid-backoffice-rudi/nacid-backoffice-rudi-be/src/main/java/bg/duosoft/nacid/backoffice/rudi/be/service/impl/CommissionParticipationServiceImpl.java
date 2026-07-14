package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionParticipationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationSaveDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionParticipationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.CommissionParticipationMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CommissionParticipationRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionMemberService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionParticipationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.CommissionCalendarParticipationValidator;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
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
public class CommissionParticipationServiceImpl extends CrudServiceBaseImpl<Integer, CommissionParticipationDTO> implements CommissionParticipationService {
    private final CommissionParticipationRepository commissionParticipationRepository;
    private final CommissionParticipationMapper commissionParticipationMapper;
    private final CommissionMemberService commissionMemberService;
    private final CommissionCalendarService commissionCalendarService;
    private final CommissionCalendarParticipationValidator commissionCalendarParticipationValidator;

    @Override
    protected CommissionParticipationRepository getRepository() {
        return commissionParticipationRepository;
    }

    @Override
    protected CommissionParticipationMapper getMapper() {
        return commissionParticipationMapper;
    }

    @Override
    protected Validator getValidator() {
        return null;
    }


    @Override
    public void saveMembers(CommissionCalendarParticipationSaveDTO dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getCalendarId())) {
            throw new RuntimeException("Incorrect dto data on save commission members!");
        }

        List<ValidationError> errors = commissionCalendarParticipationValidator.validate(dto);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        Integer calendarId = dto.getCalendarId();
        CommissionCalendarDTO existedCalendar = commissionCalendarService.selectById(calendarId);
        List<CommissionParticipationDTO> participations = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dto.getParticipations())) {
            dto.getParticipations().stream().forEach(participation -> {
                CommissionParticipationDTO commissionParticipationDTO = selectByCalendarAndMemberId(calendarId, participation.getMember().getId());
                if (Objects.isNull(commissionParticipationDTO)) {
                    commissionParticipationDTO = new CommissionParticipationDTO();
                }
                commissionParticipationDTO.setCommissionMember(commissionMemberService.selectById(participation.getMember().getId()));
                commissionParticipationDTO.setParticipated(participation.getParticipated());
                commissionParticipationDTO.setNotified(participation.getNotified());
                commissionParticipationDTO.setChairman(participation.getChairman());
                participations.add(commissionParticipationDTO);
            });
        }
        existedCalendar.setParticipations(participations);
        existedCalendar.setSecretary(dto.getSecretary());
        commissionCalendarService.update(existedCalendar);
    }

    @Override
    public List<CommissionParticipationDTO> selectByCalendarId(Integer calendarId) {
        return commissionParticipationMapper.toDtoList(commissionParticipationRepository.selectByCalendarId(calendarId));
    }

    @Override
    public CommissionParticipationDTO selectByCalendarAndMemberId(Integer calendarId, Integer memberId) {
        CommissionParticipationEntity participation = commissionParticipationRepository.selectByCalendarAndMemberId(calendarId, memberId);
        return commissionParticipationMapper.toDto(participation);
    }
}
