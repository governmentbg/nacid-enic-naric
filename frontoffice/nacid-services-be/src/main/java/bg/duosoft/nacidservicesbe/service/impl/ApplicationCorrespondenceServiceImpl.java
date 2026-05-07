package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CCorrespondenceNotificationEmailData;
import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceListFilterDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceFilter;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationCorrespondenceListFilterMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationCorrespondenceMapper;
import bg.duosoft.nacidservicesbe.repository.common.ApplicationCorrespondenceRepository;
import bg.duosoft.nacidservicesbe.service.ApplicationCorrespondenceService;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 15:47
 */
@Service
@RequiredArgsConstructor
public class ApplicationCorrespondenceServiceImpl implements ApplicationCorrespondenceService {

    private final ApplicationCorrespondenceRepository applicationCorrespondenceRepository;
    private final ApplicationCorrespondenceMapper applicationCorrespondenceMapper;
    private final ApplicationCorrespondenceListFilterMapper applicationCorrespondenceListFilterMapper;
    private final KeycloakUserService keycloakUserService;
    private final MailSenderService mailSenderService;
    private final CommonApplicationService commonApplicationService;

    @Override
    public List<ApplicationCorrespondenceDTO> getCorrespondenceForApplication(Integer applicationId) {
        List<ApplicationCorrespondenceEntity> correspondence = applicationCorrespondenceRepository.getCorrespondenceForApplicationId(applicationId);
        List<ApplicationCorrespondenceDTO> result = applicationCorrespondenceMapper.toDtoList(correspondence);
        return result;
    }

    @Override
    public List<ApplicationCorrespondenceDTO> filterAllCorrespondence(ApplicationCorrespondenceListFilterDTO filterDto) {
        ApplicationCorrespondenceFilter filter = applicationCorrespondenceListFilterMapper.toEntity(filterDto);
        List<ApplicationCorrespondenceEntity> correspondence = applicationCorrespondenceRepository.filterApplicationCorrespondence(filter);
        List<ApplicationCorrespondenceDTO> result = applicationCorrespondenceMapper.toDtoList(correspondence);
        return result;
    }

    @Override
    public Integer getTotalCorrespondenceCount(ApplicationCorrespondenceListFilterDTO filterDto) {
        ApplicationCorrespondenceFilter filter = applicationCorrespondenceListFilterMapper.toEntity(filterDto);
        Integer count = applicationCorrespondenceRepository.countFilteredApplicationCorrespondence(filter);
        return count;
    }

    @Override
    public ApplicationCorrespondenceDTO getCorrespondence(Integer id) {
        ApplicationCorrespondenceEntity correspondence = applicationCorrespondenceRepository.findById(id).orElse(null);
        if (correspondence != null) {
            return applicationCorrespondenceMapper.toDto(correspondence);
        }
        return null;
    }

    @Override
    public ApplicationCorrespondenceDTO createCorrespondence(ApplicationCorrespondenceDTO correspondenceDto) {
        ApplicationCorrespondenceEntity correspondence = applicationCorrespondenceMapper.toEntity(correspondenceDto);
        ApplicationCorrespondenceEntity saved = applicationCorrespondenceRepository.save(correspondence);
        String applicationTempNumber = commonApplicationService.getApplicationTempNumber(saved.getApplicationId());
        String applicationUserCreated = commonApplicationService.getApplicationUserCreated(saved.getApplicationId());
        sendNewCorrespondenceEmail(applicationTempNumber, applicationUserCreated);
        return applicationCorrespondenceMapper.toDto(saved);
    }

    private void sendNewCorrespondenceEmail(String applicationTempNumber, String applicationCreatedUser) {
        NacidUserDetailsDTO userDetails = keycloakUserService.getUserByUsername(applicationCreatedUser);
        mailSenderService.sendCorrespondenceNotificationMail(CCorrespondenceNotificationEmailData.builder()
                .email(userDetails.getEmail())
                .fullName(userDetails.getFullName())
                .tempNumber(applicationTempNumber)
                .build());
    }

    @Override
    public ApplicationCorrespondenceDTO readCorrespondence(Integer id) {
        ApplicationCorrespondenceEntity correspondence = applicationCorrespondenceRepository.findById(id).orElse(null);
        if (correspondence != null && correspondence.getDateRead() == null) {
            correspondence.setDateRead(LocalDateTime.now());
            ApplicationCorrespondenceEntity saved = applicationCorrespondenceRepository.save(correspondence);
            return applicationCorrespondenceMapper.toDto(saved);
        }
        return correspondence != null ? applicationCorrespondenceMapper.toDto(correspondence) : null;
    }
}
