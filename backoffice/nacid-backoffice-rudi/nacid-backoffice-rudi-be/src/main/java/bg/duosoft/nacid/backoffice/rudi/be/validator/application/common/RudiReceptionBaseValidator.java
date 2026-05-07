package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documentreceivemethod.DocumentReceiveMethodClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocumentReceiveMethod;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.base.MethodReceiveMethodValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.reception.ReceptionBaseValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiReceptionBaseValidator implements Validator<RudiApplicationDTO>, MethodReceiveMethodValidator {

    @Autowired
    private DocumentReceiveMethodClient documentReceiveMethodClient;

    @Autowired
    private ReceptionBaseValidator receptionBaseValidator;

    //TODO Inactive persons
    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<DocumentReceiveMethodDTO> allDocReceiveMethods = documentReceiveMethodClient.getAll(false);

        List<ValidationError> errors = new ArrayList<>();
        receptionBaseValidator.validate(errors, rudiApplicationDTO.getApplication(), allDocReceiveMethods);

        List<TrainingCourseSpecialityDTO> trainingCourseSpecialities = rudiApplicationDTO.getTrainingCourse().getTrainingCourseSpecialities();
        if (!CollectionUtils.isEmpty(trainingCourseSpecialities)) {
            for (int i = 0; i < trainingCourseSpecialities.size(); i++) {
                if (!StringUtils.hasText(trainingCourseSpecialities.get(i).getSpeciality()) || trainingCourseSpecialities.get(i).getSpeciality().length() > 255) {
                    reject(errors, "trainingCourseSpecialities[" + i + "].speciality", "m.validation.field.required.255");
                }
                if (StringUtils.hasText(trainingCourseSpecialities.get(i).getOriginalSpeciality()) && trainingCourseSpecialities.get(i).getOriginalSpeciality().length() > 255) {
                    reject(errors, "trainingCourseSpecialities[" + i + "].originalSpeciality", "m.validation.field.longer.than.255");
                }
            }
        }

        String manualTempUniName = rudiApplicationDTO.getTrainingCourse().getManualTempUniName();
        if (StringUtils.hasText(manualTempUniName)) {
            if (manualTempUniName.length() > 255) {
                reject(errors, "manualTempUniName", "m.validation.field.longer.than.255");
            }
            //TODO Uncomment when pending apps are accepted!
//            else {
//                if (manualTempUniName.split(",").length < 3){
//                    reject(errors, "manualTempUniName", "m.validation.field.manualTempUniName.format");
//                }
//            }
        }

        return errors;
    }

    protected void validateCertificateReceiveMethod(ApplicationDTO application, List<ValidationError> errors) {
        validateCrfCode(application, errors);
    }

}
