package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.accept;

import bg.duosoft.nacid.backoffice.core.client.client.common.address.AdminAddressClient;
import bg.duosoft.nacid.backoffice.core.client.client.common.person.AdminPersonClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptBaseDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiAcceptanceBaseMapper {

    private final AdminPersonClient adminPersonClient;
    private final AdminAddressClient adminAddressClient;
    private final UniversityService universityService;

    public void processBaseMapping(RudiAcceptBaseDTO acceptDTO, RudiApplicationDTO application) {
        setApplicant(acceptDTO, application);
        setRepresentative(acceptDTO, application);
        setRepresentativeCompany(acceptDTO, application);
        setContactAddress(acceptDTO, application);
        setBaseUniversity(acceptDTO, application);
    }

    private void setApplicant(RudiAcceptBaseDTO acceptDTO, RudiApplicationDTO application) {
        Integer applicantId = acceptDTO.getApplicantId();
        if (Objects.isNull(applicantId)) {
            application.getApplication().setApplicant(null);
            return;
        }
        application.getApplication().setApplicant(selectPersonById(applicantId));
    }

    private void setRepresentative(RudiAcceptBaseDTO acceptDTO, RudiApplicationDTO application) {
        Integer representativeId = acceptDTO.getRepresentativeId();
        if (Objects.isNull(representativeId)) {
            application.getApplication().setRepresentative(null);
            return;
        }
        application.getApplication().setRepresentative(selectPersonById(representativeId));
    }


    private void setRepresentativeCompany(RudiAcceptBaseDTO acceptDTO, RudiApplicationDTO application) {
        Boolean representativeCompanyFlag = acceptDTO.getRepresentativeCompanyFlag();
        if (Objects.isNull(representativeCompanyFlag) || !representativeCompanyFlag) {
            application.getApplication().setRepresentativeCompany(null);
            return;
        }

        Integer representativeCompanyId = acceptDTO.getRepresentativeCompanyId();
        if (Objects.isNull(representativeCompanyId)) {
            application.getApplication().setRepresentativeCompany(null);
            return;
        }
        application.getApplication().setRepresentativeCompany(selectPersonById(representativeCompanyId));
    }

    private void setContactAddress(RudiAcceptBaseDTO acceptDTO, RudiApplicationDTO application) {
        Integer contactAddressId = acceptDTO.getContactAddressId();
        if (Objects.isNull(contactAddressId)) {
            application.getApplication().setContactAddress(null);
            return;
        }
        application.getApplication().setContactAddress(selectAddressById(contactAddressId));
    }

    private void setBaseUniversity(RudiAcceptBaseDTO acceptDTO, RudiApplicationDTO application) {
        Integer baseUniversityId = acceptDTO.getBaseUniversityId();
        if (Objects.isNull(baseUniversityId)) {
            TrainingCourseDTO trainingCourse = application.getTrainingCourse();
            if (Objects.nonNull(trainingCourse)) {
                trainingCourse.setBaseUniversity(null);
            }
            return;
        }

        TrainingCourseDTO trainingCourse = application.getTrainingCourse();
        if (Objects.isNull(trainingCourse)) {
            application.setTrainingCourse(new TrainingCourseDTO());
        }

        UniversityDTO baseUniversity = selectUniversityById(baseUniversityId);
        application.getTrainingCourse().setBaseUniversity(baseUniversity);

        TrainingCourseUniversityDTO trainingCourseUniversity = new TrainingCourseUniversityDTO();
        trainingCourseUniversity.setOrdNum(1);
        trainingCourseUniversity.setUniversity(baseUniversity);
        trainingCourseUniversity.setUniversityNameTranslated(baseUniversity.getBgName());
        trainingCourse.setTrainingCourseUniversities(Collections.singletonList(trainingCourseUniversity));
    }


    @NotNull
    public PersonDTO selectPersonById(Integer id) {
        PersonDTO person = adminPersonClient.selectById(id);
        if (Objects.isNull(person)) {
            throw new RuntimeException("[E-APPS ACCEPTANCE] Cannot find person! Person ID: " + id);
        }
        return person;
    }

    @NotNull
    public AddressDTO selectAddressById(Integer id) {
        AddressDTO address = adminAddressClient.selectById(id);
        if (Objects.isNull(address)) {
            throw new RuntimeException("[E-APPS ACCEPTANCE] Cannot find address! Address ID: " + id);
        }
        return address;
    }

    @NotNull
    private UniversityDTO selectUniversityById(Integer id) {
        UniversityDTO university = universityService.selectById(id);
        if (Objects.isNull(university)) {
            throw new RuntimeException("[E-APPS ACCEPTANCE] Cannot find university! University ID: " + id);
        }
        return university;
    }


}
