package bg.duosoft.nacidservicesbe.validation.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.validation.base.EikValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:42
 */
@Component
@RequiredArgsConstructor
public class RudiApplicantDetailsValidator implements EikValidator<RudiApplicantDetailsDTO> {

    private final CommonApplicantDetailsValidator commonApplicantDetailsValidator;

    @Override
    public List<ValidationError> validate(RudiApplicantDetailsDTO applicantDetails, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        errors.addAll(commonApplicantDetailsValidator.validate(applicantDetails));

        commonApplicantDetailsValidator.validateContactAddress(errors, applicantDetails.getContactAddress(), true);

        if(applicantDetails.isDiplomaNamesDifferent()) {
            if(applicantDetails.getDiplomaNames() == null){
                errors.add(ValidationError.builder().pointer("diplomaNames").message(ValidationMessageCodes.REQUIRED_CODE).build());
            } else {
                CountryDTO bgCountry = new CountryDTO();
                bgCountry.setId(DefaultValue.BG_COUNTRY_CODE);
                commonApplicantDetailsValidator.validateNaturalPersonNames(errors, applicantDetails.getDiplomaNames(), PersonalIdentifierType.NATIONAL_ID, bgCountry, null, "diplomaNames");
            }
        }

        rejectIfEmptyCollection(errors, applicantDetails.getCertificateReceiveForms(), "certificateReceiveForms", ValidationMessageCodes.REQUIRED_CODE);

        return errors;
    }

}
