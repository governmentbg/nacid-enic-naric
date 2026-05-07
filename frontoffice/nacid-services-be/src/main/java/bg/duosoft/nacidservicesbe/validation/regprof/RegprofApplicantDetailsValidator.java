package bg.duosoft.nacidservicesbe.validation.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.applicantdetails.CommonApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.common.person.PersonsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.12.2022
 * Time: 16:24
 */
@Component
@RequiredArgsConstructor
public class RegprofApplicantDetailsValidator implements PersonsValidator<RegprofApplicantDetailsDTO> {

    private final CommonApplicantDetailsValidator commonApplicantDetailsValidator;

    @Override
    public List<ValidationError> validate(RegprofApplicantDetailsDTO regprofApplicantDetailsDTO, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();

        errors.addAll(commonApplicantDetailsValidator.validate(regprofApplicantDetailsDTO));

        commonApplicantDetailsValidator.validateContactAddress(errors, regprofApplicantDetailsDTO.getContactAddress(), true);

        if(regprofApplicantDetailsDTO.isQualificationNamesDifferent()) {
            rejectIfEmpty(errors, regprofApplicantDetailsDTO.getQualificationNames(), "qualificationNames", ValidationMessageCodes.REQUIRED_CODE);
            if(regprofApplicantDetailsDTO.getQualificationNames() != null){
                CountryDTO bgCountry = new CountryDTO();
                bgCountry.setId(DefaultValue.BG_COUNTRY_CODE);
                commonApplicantDetailsValidator.validateNaturalPersonNames(errors, regprofApplicantDetailsDTO.getQualificationNames(), regprofApplicantDetailsDTO.getQualificationNames().getPersonalIdType(), bgCountry, null, "qualificationNames");
                validatePersonalId(errors, regprofApplicantDetailsDTO.getQualificationNames(), "qualificationNames");
            }
        }
        rejectIfEmptyCollection(errors, regprofApplicantDetailsDTO.getCertificateReceiveForms(), "certificateReceiveForms", ValidationMessageCodes.REQUIRED_CODE);

        return errors;
    }
}
