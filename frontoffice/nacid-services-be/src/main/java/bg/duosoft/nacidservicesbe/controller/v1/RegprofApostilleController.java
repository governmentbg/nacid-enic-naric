package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomain;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApostilleApplicationDTO;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesService;
import bg.duosoft.nacidservicesbe.service.RegprofApostilleService;
import bg.duosoft.nacidservicesbe.utils.NomenclatureConstants;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.regprofapostille.RegprofApostilleApplicationValidator;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2023
 * Time: 12:05
 */
@Slf4j
@RestController
@Api(tags = Tags.REGPROF_APOSTILLE)
@RequestMapping("/api/v1/regprof-apostille")
@RequiredArgsConstructor
@PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).REGPROF_APOSTILLE_APPLICATION_CREATE)")
public class RegprofApostilleController {

    private final RegprofApostilleApplicationValidator regprofApostilleApplicationValidator;
    private final RegprofApostilleService regprofApostilleService;
    private final BoPublicServicesService boPublicServicesService;

    @PostMapping("/create")
    public RegprofApostilleApplicationDTO createRegprofApostilleApplication(@RequestBody RegprofApostilleApplicationDTO application){
        fixInvalidApplicant(application);
        BadRequestValidator.validateRequest(regprofApostilleApplicationValidator, application);
        preSaveRegprofApostilleApplication(application);
        RegprofApostilleApplicationDTO saved = regprofApostilleService.createRegprofApostilleApplication(application);
        return saved;
    }

    private void fixInvalidApplicant(RegprofApostilleApplicationDTO application){
        if(application.getApplicantDetails() != null && application.getApplicantDetails().getApplicant() != null &&
            application.getApplicantDetails().getApplicant().getNaturalPerson() != null){
            NaturalPersonDTO applicantNatural = application.getApplicantDetails().getApplicant().getNaturalPerson();
            if(applicantNatural.getPersonalIdType() != null && applicantNatural.getPersonalIdType().equals(PersonalIdentifierType.NATIONAL_FOREIGNER_ID)
                && StringUtils.hasText(applicantNatural.getPersonalId())
                && CivilIdUtils.validateLNCH(applicantNatural.getPersonalId()) == false){
                log.warn("Invalid NATIONAL_FOREIGN_ID received for apostille app - {}. Changing it to DOCUMENT_ID and FOREIGN_IDENTIFIER_TYPE {}", applicantNatural.getPersonalId(), NomenclatureConstants.FOREIGN_IDENTIFIER_TYPE_PERSONAL_DOC_NUMBER);
                applicantNatural.setPersonalIdType(PersonalIdentifierType.DOCUMENT_ID);
                applicantNatural.setForeignerIdentifierKind(new ReferenceDataDTO(ReferenceDataDomain.FOREIGN_IDENTIFIER_TYPE.name(), NomenclatureConstants.FOREIGN_IDENTIFIER_TYPE_PERSONAL_DOC_NUMBER));
                applicantNatural.setForeignerIdentifierCountry(new CountryDTO(NomenclatureConstants.COUNTRY_UNKNOWN, null, null, null));
            }
        }
    }

    public void preSaveRegprofApostilleApplication(RegprofApostilleApplicationDTO application){
        RegprofController.preSaveRegprofApplicantDetails(application.getApplicantDetails(), boPublicServicesService);
        RegprofController.preSaveRegprofEducationDetails(application.getEducationDetails());
        BaseApplicationController.preSaveDocumentDetails(application.getDocumentDetails());
    }
}
