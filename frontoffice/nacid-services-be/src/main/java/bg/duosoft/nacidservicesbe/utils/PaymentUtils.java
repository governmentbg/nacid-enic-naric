package bg.duosoft.nacidservicesbe.utils;

import bg.duosoft.nacidfrontofficedto.person.CompanyDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.person.UniversityDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.09.2023
 * Time: 15:04
 */
public class PaymentUtils {

    public static String createPayerName(CommonApplicationDTO application){
        return switch (application.getApplicantDetails().getApplicant().getApplicantType()){
            case NATURAL_PERSON -> createPayerName(application.getApplicantDetails().getApplicant().getNaturalPerson());
            case COMPANY -> createPayerName(application.getApplicantDetails().getApplicant().getCompany());
            case UNIVERSITY -> createPayerName(application.getApplicantDetails().getApplicant().getUniversity());
        };
    }

    public static String createPayerName(NaturalPersonDTO naturalPerson){
        return String.format("%s, %s %s", naturalPerson.getFullName(), naturalPerson.getPersonalIdTypeName(), naturalPerson.getPersonalId());
    }

    public static String createPayerName(CompanyDTO company){
        return String.format("%s, %s %s", company.getCompanyName(), company.getCompanyIdentifierTypeName(), company.getCompanyIdentifier());
    }

    public static String createPayerName(UniversityDTO university){
        return String.format("%s, %s %s", university.getUniversityName(), university.getUniversityIdentifierTypeName(), university.getUniversityIdentifier());
    }

    public static String createUniChecksPayerName(UniChecksApplicationDTO uniCheckApp){
        String applicantName = switch (uniCheckApp.getApplicantDetails().getApplicant().getApplicantType()){
            case NATURAL_PERSON -> uniCheckApp.getApplicantDetails().getApplicant().getNaturalPerson().getFullName();
            case COMPANY -> uniCheckApp.getApplicantDetails().getApplicant().getCompany().getCompanyName();
            case UNIVERSITY -> uniCheckApp.getApplicantDetails().getApplicant().getUniversity().getUniversityName();
        };

        String ownerName = uniCheckApp.getEducationDetails().getDiplomaHolder().getFullName();
        return String.format("%s, %s", applicantName, ownerName);
    }
}
