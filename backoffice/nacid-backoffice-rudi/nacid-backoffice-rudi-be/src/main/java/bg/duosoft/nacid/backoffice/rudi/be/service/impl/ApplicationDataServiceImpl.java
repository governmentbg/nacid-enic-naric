package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.common.address.AdminAddressClient;
import bg.duosoft.nacid.backoffice.core.client.client.common.person.AdminPersonClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationDataService;
import bg.duosoft.nacidbackofficeshareddata.service.impl.ApplicationDataBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationDataServiceImpl extends ApplicationDataBaseServiceImpl implements ApplicationDataService {
    private final AdminPersonClient adminPersonClient;
    @Override
    public void fillFullPersonAndAddressData(RudiApplicationDTO rudiApplication) {
        if (Objects.isNull(rudiApplication)) {
            return;
        }

        ApplicationDTO application = rudiApplication.getApplication();
        if (Objects.isNull(application)) {
            return;
        }

        super.fillFullPersonAndAddressData(application);

        TrainingCourseDTO trainingCourse = rudiApplication.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            PersonDTO diplomaOwner = trainingCourse.getDiplomaOwner();
            if (Objects.nonNull(diplomaOwner) && Objects.nonNull(diplomaOwner.getId())) {
                PersonDTO result = adminPersonClient.selectById(diplomaOwner.getId());
                if (Objects.nonNull(result)) {
                    application.setRepresentative(result);
                }
            }
        }

    }
}
