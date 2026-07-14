package bg.duosoft.nacidcoreapi.cron.national_university;

import bg.duosoft.nacidcoreapi.integration.register.national_university.client.NationalUniversityRegisterClient;
import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityModel;
import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityRequest;
import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityResponse;
import bg.duosoft.nacidcoreapi.service.cron.national_university.NationalUniversitySyncService;
import bg.duosoft.nacidcoreapi.service.nomenclature.NationalUniversityService;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bg.duosoft.nacidshareddata.util.date.DateUtils.DATE_TIME_FORMAT_UTC;

@Slf4j
@Component
@RequiredArgsConstructor
public class NationalUniversitySyncCron {

    private final NationalUniversityService nationalUniversityService;
    private final NationalUniversitySyncService nationalUniversitySyncService;
    private final NationalUniversityRegisterClient nationalUniversityRegisterClient;

    @Scheduled(cron = "0 0 3 3 * *")
    public void reportCurrentTime() {
        log.info("National university synchronization cron: start time is {}", new SimpleDateFormat(DATE_TIME_FORMAT_UTC).format(new Date()));
        synchronizeNationalUniversities();
        log.info("National university synchronization cron: end time is {}", new SimpleDateFormat(DATE_TIME_FORMAT_UTC).format(new Date()));
    }

    private void synchronizeNationalUniversities() {
        // institutionActiveStatus: 1 = only active records, institutionSearchType: 1 = only universities
        NationalUniversityResponse response = nationalUniversityRegisterClient.selectNationalUniversitiesInfo(new NationalUniversityRequest(1, 1, 10000));

        if (Objects.nonNull(response) && !CollectionUtils.isEmpty(response.getResult())) {
            List<NationalUniversityDTO> activeNationalUniversities = nationalUniversityService.selectAll(true);
            log.info("National university synchronization cron: Active national universities before synchronization: Count: " + activeNationalUniversities.size() + " - IDs: "+ activeNationalUniversities.stream().map(e-> e.getId()).collect(Collectors.joining(" ; ")));

            nationalUniversityService.updateAllToInactive();
            List<NationalUniversityDTO> nationalUniversities = nationalUniversityService.selectAll(false);

            List<NationalUniversityModel> registerUniversities = response.getResult();
            for (NationalUniversityModel model : registerUniversities) {
                try {
                    String registerUniversityEik = model.getUic();
                    NationalUniversityDTO nationalUniversity = nationalUniversities.stream().filter(u -> u.getId().equals(registerUniversityEik)).findFirst().orElse(null);
                    nationalUniversitySyncService.syncNationalUniversityInfo(model, nationalUniversity);
                } catch (Exception e) {
                    log.error("Error synchronizing national university with EIK = " + model.getUic(), e);
                }
            }
        }
    }
}
