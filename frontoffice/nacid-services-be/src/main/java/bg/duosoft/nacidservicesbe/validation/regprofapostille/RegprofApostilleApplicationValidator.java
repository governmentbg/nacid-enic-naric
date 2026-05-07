package bg.duosoft.nacidservicesbe.validation.regprofapostille;

import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApostilleApplicationDTO;
import bg.duosoft.nacidservicesbe.validation.common.documents.DocumentDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.regprof.RegprofEducationDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.06.2023
 * Time: 14:45
 */
@Component
@RequiredArgsConstructor
public class RegprofApostilleApplicationValidator implements Validator<RegprofApostilleApplicationDTO> {

    private final RegprofApostilleApplicantDetailsValidator regprofApostilleApplicantDetailsValidator;
    private final RegprofEducationDetailsValidator regprofEducationDetailsValidator;
    private final DocumentDetailsValidator documentDetailsValidator;

    @Override
    public List<ValidationError> validate(RegprofApostilleApplicationDTO application, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if(application.getApplicantDetails() == null || application.getEducationDetails() == null || application.getDocumentDetails() == null){
            rejectIfEmpty(errors, application.getApplicantDetails(), "applicantDetails", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfEmpty(errors, application.getEducationDetails(), "educationDetails", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfEmpty(errors, application.getDocumentDetails(), "documentDetails", ValidationMessageCodes.REQUIRED_CODE);
        } else {
            List<ValidationError> applicantDetailsErrors = regprofApostilleApplicantDetailsValidator.validate(application.getApplicantDetails());
            List<ValidationError> educationDetailsErrors = regprofEducationDetailsValidator.validate(application.getEducationDetails());
            List<ValidationError> documentDetailsErrors = documentDetailsValidator.validate(application.getDocumentDetails());

            addErrors(errors, applicantDetailsErrors, "applicantDetails");
            addErrors(errors, educationDetailsErrors, "educationDetails");
            addErrors(errors, documentDetailsErrors, "documentDetails");
        }

        if(Boolean.TRUE.equals(application.getPaid())){
            rejectIfEmptyString(errors, application.getPaymentTypeCode(), "paymentTypeCode", ValidationMessageCodes.REQUIRED_CODE);
        }

        return errors;
    }

    private void addErrors(List<ValidationError> targetList, List<ValidationError> sourceList, String appendablePointer){
        targetList.addAll(sourceList.stream().map(err -> ValidationError.create(appendablePointer+"."+err.getPointer(), err.getMessage())).collect(Collectors.toList()));
    }
}
