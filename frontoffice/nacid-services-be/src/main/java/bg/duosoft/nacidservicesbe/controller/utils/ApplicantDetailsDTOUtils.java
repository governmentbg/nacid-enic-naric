package bg.duosoft.nacidservicesbe.controller.utils;

import bg.duosoft.nacidcoredata.enums.ForeignIdentifierType;
import bg.duosoft.nacidfrontofficedto.address.BaseAddress;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.person.WithPersonalIdentifier;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesService;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import org.springframework.util.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.11.2022
 * Time: 13:26
 */
public class ApplicantDetailsDTOUtils {

    public static void preSaveRudiApplicantDetails(RudiApplicantDetailsDTO rudiApplicantDetails, BoPublicServicesService boPubService){
        if(!rudiApplicantDetails.isDiplomaNamesDifferent()){
            rudiApplicantDetails.setDiplomaNames(null);
        }
        preSaveCommonApplicantDetails(rudiApplicantDetails, boPubService);
        if(!rudiApplicantDetails.isApplicantHasRepresentative()){
            rudiApplicantDetails.setRepresentativeCompanyIdentifier(null);
        }
    }

    public static void preSaveCommonApplicantDetails(CommonApplicantDetailsDTO commonApplicantDetails, BoPublicServicesService boPubService){
        if(commonApplicantDetails.getApplicant() != null){
            if(commonApplicantDetails.getApplicant().getApplicantType() != null) {
                if (commonApplicantDetails.getApplicant().getApplicantType().equals(ApplicantType.NATURAL_PERSON)) {
                    commonApplicantDetails.getApplicant().setCompany(null);
                } else if (commonApplicantDetails.getApplicant().getApplicantType().equals(ApplicantType.COMPANY)) {
                    commonApplicantDetails.getApplicant().setNaturalPerson(null);
                }
            }

            preSaveNaturalPerson(commonApplicantDetails.getApplicant().getNaturalPerson(), boPubService);
        }
        if(!commonApplicantDetails.isApplicantHasRepresentative()){
            commonApplicantDetails.setRepresentative(null);
            commonApplicantDetails.setRepresentativeCapacity(null);
        }
        preSaveNaturalPerson(commonApplicantDetails.getRepresentative(), boPubService);

        preSaveAddress(commonApplicantDetails.getContactAddress());

        preSaveApplicationDocumentReceiveMethod(commonApplicantDetails.getResultReceive());
        preSaveApplicationDocumentReceiveMethod(commonApplicantDetails.getResultReceiveElectronic());
        preSaveApplicationDocumentReceiveMethod(commonApplicantDetails.getResultReceivePaper());
    }

    public static void preSaveApplicationDocumentReceiveMethod(ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethod){
        if(applicationDocumentReceiveMethod != null){
            if(applicationDocumentReceiveMethod.getResultReceive() != null &&
                    (applicationDocumentReceiveMethod.getResultReceive().getDocumentRecipient() == null || !applicationDocumentReceiveMethod.getResultReceive().getDocumentRecipient())) {
                applicationDocumentReceiveMethod.setReceiverAddress(null);
            }
            preSaveAddress(applicationDocumentReceiveMethod.getReceiverAddress());
        }
    }

    public static void preSaveNaturalPerson(NaturalPersonDTO naturalPerson, BoPublicServicesService boPubService){
        if(naturalPerson != null) {
            if(naturalPerson.getHumanitarianStatus() != null && !StringUtils.hasText(naturalPerson.getHumanitarianStatus().getId())){
                naturalPerson.setHumanitarianStatus(null);
            }
            if (naturalPerson.getBirthCountry() != null && !DefaultValue.BG_COUNTRY_CODE.equals(naturalPerson.getBirthCountry().getId())) {
                naturalPerson.setBirthSettlement(null);
            }
            if(naturalPerson.getBirthCountry() != null && naturalPerson.getBirthCountry().getId() == null){
                naturalPerson.setBirthCountry(null);
            }
            if(naturalPerson.getCitizenship() != null && naturalPerson.getCitizenship().getId() == null){
                naturalPerson.setCitizenship(null);
            }
            if(naturalPerson.getBirthSettlement() != null && naturalPerson.getBirthSettlement().getId() == null){
                naturalPerson.setBirthSettlement(null);
            }
            preSaveWithIdentifier(naturalPerson, boPubService);
        }
    }

    public static void preSaveWithIdentifier(WithPersonalIdentifier withId, BoPublicServicesService boPubService){
        if(withId != null){
            if((withId.getPersonalIdType() == null || !withId.getPersonalIdType().equals(PersonalIdentifierType.DOCUMENT_ID))) {
                withId.setForeignerIdentifierCountry(null);
                withId.setForeignerIdentifierKind(null);
            } else {
                if(withId.getForeignerIdentifierKind() != null && withId.getForeignerIdentifierKind().getId().equals(ForeignIdentifierType.OFFICIALLY_GENERATED_BY_NACID.getCode())){
                    if(!StringUtils.hasText(withId.getPersonalNacidId())) {
                        //TODO should I wrap the call in case it throws 404 not to show in page not found?
                        withId.setPersonalNacidId(boPubService.generatePersonalNacidIdentifier());
                    }
                    if(withId.getForeignerIdentifierCountry() == null){
                        withId.setForeignerIdentifierCountry(new CountryDTO());
                    }
                    if(!StringUtils.hasText(withId.getForeignerIdentifierCountry().getId())){
                        withId.getForeignerIdentifierCountry().setId(DefaultValue.BG_COUNTRY_CODE);
                    }
                }
            }
        }
    }

    public static void preSaveAddress(BaseAddress address){
        if(address != null){
            if(address.getCountry() != null && !StringUtils.hasText(address.getCountry().getId())){
                address.setCountry(null);
            }
            if(address.getSettlement() != null &&!StringUtils.hasText(address.getSettlement().getId())){
                address.setSettlement(null);
            }
            if(address.getCountry() != null && !DefaultValue.BG_COUNTRY_CODE.equals(address.getCountry().getId())){
                address.setSettlement(null);
            }
            if(address.getCountry() != null && DefaultValue.BG_COUNTRY_CODE.equals(address.getCountry().getId())){
                address.setCity(null);
            }
            if(address.getCountry() == null){
                address.setSettlement(null);
                address.setCity(null);
            }
        }
    }
}
