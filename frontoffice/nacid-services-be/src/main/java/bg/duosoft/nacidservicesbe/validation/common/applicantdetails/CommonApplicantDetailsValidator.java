package bg.duosoft.nacidservicesbe.validation.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacidservicesbe.utils.NomenclatureConstants;
import bg.duosoft.nacidservicesbe.validation.common.address.AddressesValidator;
import bg.duosoft.nacidservicesbe.validation.common.person.PersonsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.09.2022
 * Time: 12:31
 */
@Component
@RequiredArgsConstructor
public class CommonApplicantDetailsValidator implements AddressesValidator<CommonApplicantDetailsDTO>,
        PersonsValidator<CommonApplicantDetailsDTO> {

    @Override
    public List<ValidationError> validate(CommonApplicantDetailsDTO applicantDetails, Object... args) {
        Boolean validateRepresentativeCapacity = args != null && args.length >0 && args[0] != null && args[0] instanceof Boolean ? (Boolean) args[0] : false;
        Boolean validateNaturalPersonBirthPlaceAndCitizenship = args != null && args.length >1 && args[1] != null && args[1] instanceof Boolean ? (Boolean) args[1] : true;
        Boolean validateBirthDate = args != null && args.length >2 && args[2] != null && args[2] instanceof Boolean ? (Boolean) args[2] : true;
        List<ValidationError> errors = new ArrayList<>();

        validateApplicant(errors, applicantDetails, validateNaturalPersonBirthPlaceAndCitizenship, validateBirthDate);
        validateRepresentative(errors, applicantDetails, validateNaturalPersonBirthPlaceAndCitizenship, validateRepresentativeCapacity, validateBirthDate);

        validateApplicationDocReceiveMethods(errors, applicantDetails);

        validateApplicantTitles(errors, applicantDetails);

        return errors;
    }

    public void validateApplicantTitles(List<ValidationError> errors, CommonApplicantDetailsDTO applicantDetails) {
        rejectIfStringLengthBigger(errors, applicantDetails.getApplicantTitleBefore(), 50, "applicantTitleBefore");
        rejectIfStringLengthBigger(errors, applicantDetails.getApplicantTitleAfter(), 50, "applicantTitleAfter");
    }

    public void validateApplicationDocReceiveMethods(List<ValidationError> errors, CommonApplicantDetailsDTO applicantDetails) {
        if(applicantDetails.getCertificateReceiveForms() == null){
            validateApplicationDocReceiveMethod(errors, applicantDetails.getResultReceive(), "resultReceive");
        }
        if(applicantDetails.getCertificateReceiveForms() != null && applicantDetails.getCertificateReceiveForms().stream().anyMatch(cert -> cert != null && cert.equals(NomenclatureConstants.CERTIFICATE_RECEIVE_FORM_ELECTRONIC))){
            validateApplicationDocReceiveMethod(errors, applicantDetails.getResultReceiveElectronic(), "resultReceiveElectronic");
        }
        if(applicantDetails.getCertificateReceiveForms() != null && applicantDetails.getCertificateReceiveForms().stream().anyMatch(cert -> cert != null && cert.equals(NomenclatureConstants.CERTIFICATE_RECEIVE_FORM_PAPER))){
            validateApplicationDocReceiveMethod(errors, applicantDetails.getResultReceivePaper(), "resultReceivePaper");
        }
    }

    public void validateApplicationDocReceiveMethod(List<ValidationError> errors, ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethod, String basePointer){
        rejectIfEmptyString(errors, applicationDocumentReceiveMethod != null && applicationDocumentReceiveMethod.getResultReceive() != null ? applicationDocumentReceiveMethod.getResultReceive().getId() : null, basePointer+".resultReceive.id", ValidationMessageCodes.REQUIRED_CODE);
        validateReceiverAddress(errors, applicationDocumentReceiveMethod != null ? applicationDocumentReceiveMethod.getReceiverAddress(): null, applicationDocumentReceiveMethod.getResultReceive(), basePointer);
    }

    public void validateApplicant(List<ValidationError> errors, CommonApplicantDetailsDTO applicantDetails, Boolean validateNaturalPersonBirthPlaceAndCitizenship, Boolean validateBirthDate){
        if(applicantDetails.getApplicant() != null) {
            if(applicantDetails.getApplicant().getApplicantType().equals(ApplicantType.NATURAL_PERSON)) {
                validateNaturalPerson(errors, applicantDetails.getApplicant().getNaturalPerson(), "applicant.naturalPerson", validateNaturalPersonBirthPlaceAndCitizenship, validateBirthDate);
            } else if(applicantDetails.getApplicant().getApplicantType().equals(ApplicantType.COMPANY)) {
                validateCompany(errors, applicantDetails.getApplicant().getCompany(), "applicant.company");
            } else if(applicantDetails.getApplicant().getApplicantType().equals(ApplicantType.UNIVERSITY)) {
                validateUniversity(errors, applicantDetails.getApplicant().getUniversity(), "applicant.university");
            }
        }
    }

    public void validateRepresentative(List<ValidationError> errors, CommonApplicantDetailsDTO applicantDetails, Boolean validateNaturalPersonBirthPlaceAndCitizenship, Boolean validateRepresentativeCapacity, Boolean validateBirthDate){
        if(applicantDetails.isApplicantHasRepresentative()) {
            validateNaturalPerson(errors, applicantDetails.getRepresentative(), "representative", validateNaturalPersonBirthPlaceAndCitizenship, validateBirthDate);
            if(validateRepresentativeCapacity){
                rejectIfEmptyString(errors, applicantDetails.getRepresentativeCapacity(), "representativeCapacity", ValidationMessageCodes.REQUIRED_CODE);
            }
            rejectIfStringLengthBigger(errors, applicantDetails.getRepresentativeCapacity(), 255, "representativeCapacity");
            if(StringUtils.hasText(applicantDetails.getRepresentativeCompanyIdentifier())){
                validateEIK(errors, applicantDetails.getRepresentativeCompanyIdentifier(), "representativeCompanyIdentifier");
            }
        }
    }
}
