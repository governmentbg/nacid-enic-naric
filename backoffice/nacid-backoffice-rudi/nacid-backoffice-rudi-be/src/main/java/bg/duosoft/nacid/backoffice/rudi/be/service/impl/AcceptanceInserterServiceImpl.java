package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.accept_app.BoAdminAcceptApplicationClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.AcceptanceInserterService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AcceptanceInserterServiceImpl implements AcceptanceInserterService {

    private final BoAdminAcceptApplicationClient adminAcceptApplicationClient;

    @Override
    public void insertData(RudiApplicationDTO receptionApp) {
        processNomenclatureRecords(receptionApp);
        processFiles(receptionApp.getApplication());
    }

    private void processNomenclatureRecords(RudiApplicationDTO receptionApp) {
        removeNomenclaturesWithEmptyIdFromFO(receptionApp);
    }

    private void removeNomenclaturesWithEmptyIdFromFO(RudiApplicationDTO receptionApp) {
        TrainingCourseDTO trainingCourse = receptionApp.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            UniversityDTO prevDiplomaUniversity = trainingCourse.getPrevDiplomaUniversity();
            if (Objects.nonNull(prevDiplomaUniversity)) {
                if (Objects.isNull(prevDiplomaUniversity.getId())) {
                    trainingCourse.setPrevDiplomaUniversity(null);
                }
            }
        }
    }

    private void processFiles(ApplicationDTO application) {
        List<AttachedDocDTO> attachments = adminAcceptApplicationClient.processFiles(application);
        if (!CollectionUtils.isEmpty(attachments)) {
            application.setAttachments(attachments);
        }
    }

}
