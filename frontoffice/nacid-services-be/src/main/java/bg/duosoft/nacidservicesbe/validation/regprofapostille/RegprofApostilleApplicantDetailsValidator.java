package bg.duosoft.nacidservicesbe.validation.regprofapostille;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.address.AddressesValidator;
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
 * Date: 27.07.2023
 * Time: 11:37
 */
@Component
@RequiredArgsConstructor
public class RegprofApostilleApplicantDetailsValidator implements AddressesValidator<RegprofApplicantDetailsDTO>,
        PersonsValidator<RegprofApplicantDetailsDTO> {

    private final CommonApplicantDetailsValidator commonApplicantDetailsValidator;

    @Override
    public List<ValidationError> validate(RegprofApplicantDetailsDTO regprofApplicantDetailsDTO, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        commonApplicantDetailsValidator.validateApplicant(errors, regprofApplicantDetailsDTO, true, shouldValidateApplicantBirthDate(regprofApplicantDetailsDTO));
        commonApplicantDetailsValidator.validateRepresentative(errors, regprofApplicantDetailsDTO, false, false, false);
        commonApplicantDetailsValidator.validateContactAddress(errors, regprofApplicantDetailsDTO.getContactAddress(), false);
        commonApplicantDetailsValidator.validateApplicationDocReceiveMethods(errors, regprofApplicantDetailsDTO);
        commonApplicantDetailsValidator.validateApplicantTitles(errors, regprofApplicantDetailsDTO);

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

    private boolean shouldValidateApplicantBirthDate(RegprofApplicantDetailsDTO applicantDetailsDTO){
        if(applicantDetailsDTO != null &&
                applicantDetailsDTO.getApplicant() != null &&
                applicantDetailsDTO.getApplicant().getNaturalPerson() != null &&
                applicantDetailsDTO.getApplicant().getNaturalPerson().getPersonalIdType() != null){
            return applicantDetailsDTO.getApplicant().getNaturalPerson().getPersonalIdType().equals(PersonalIdentifierType.NATIONAL_ID);
        }
        return false;
    }
}
