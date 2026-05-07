package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.02.2023
 * Time: 14:36
 */
@Api(tags = Tags.AUTOCOMPLETE)
@RestController
@RequestMapping("/api/v1/autocomplete")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class AutocompleteController {

    private final BoPublicServicesService boPublicServicesService;

    @GetMapping("/universities")
    public List<BaseAutocompleteDTO> autocompleteUniversities(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteUniversities(name, page, pageSize);
        return result;
    }

    @GetMapping("/university-faculties")
    public List<BaseAutocompleteDTO> autocompleteUniversityFaculties(@RequestParam(required = false) Integer universityId, @RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        if(universityId == null){
            return new ArrayList<>();
        }
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteUniversityFaculties(universityId, name, page, pageSize);
        return result;
    }

    @GetMapping("/specialities")
    public List<BaseAutocompleteDTO> autocompleteSpecialities(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteSpecialities(name, page, pageSize);
        return result;
    }

    @GetMapping("/original-specialities")
    public List<BaseAutocompleteDTO> autocompleteOriginalSpecialities(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteOriginalSpecialities(name, page, pageSize);
        return result;
    }

    @GetMapping("/qualifications")
    public List<BaseAutocompleteDTO> autocompleteQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteQualifications(name, page, pageSize);
        return result;
    }

    @GetMapping("/original-qualifications")
    public List<BaseAutocompleteDTO> autocompleteOriginalQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteOriginalQualifications(name, page, pageSize);
        return result;
    }

    @GetMapping("/higher-specialities")
    public List<BaseAutocompleteDTO> autocompleteHigherSpecialities(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteHigherSpecialities(name, page, pageSize);
        return result;
    }

    @GetMapping("/higher-qualifications")
    public List<BaseAutocompleteDTO> autocompleteHigherQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteHigherQualifications(name, page, pageSize);
        return result;
    }

    @GetMapping("/sdk-specialities")
    public List<BaseAutocompleteDTO> autocompleteSdkSpecialities(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteSdkSpecialities(name, page, pageSize);
        return result;
    }

    @GetMapping("/sdk-qualifications")
    public List<BaseAutocompleteDTO> autocompleteSdkQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteSdkQualifications(name, page, pageSize);
        return result;
    }

    @GetMapping("/secondary-specialities")
    public List<BaseAutocompleteDTO> autocompleteSecondarySpecialities(@RequestParam(required = false) Integer qualificationId, @RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        if(qualificationId == null){
            return new ArrayList<>();
        }
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteSecondarySpecialities(qualificationId, name, page, pageSize);
        return result;
    }

    @GetMapping("/secondary-qualifications")
    public List<BaseAutocompleteDTO> autocompleteSecondaryQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteSecondaryQualifications(name, page, pageSize);
        return result;
    }

    @GetMapping("/prof-institutions")
    public List<BaseAutocompleteDTO> autocompleteProfInstitutions(@RequestParam(required = false) EducationType educationType, @RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        if(educationType == null){
            return new ArrayList<>();
        }
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteProfInstitutions(educationType, name, page, pageSize);
        return result;
    }

    @GetMapping("/prof-institution-former-names")
    public List<BaseAutocompleteDTO> autocompleteProfInstitutionFormerNames(@RequestParam(required = false) Integer profInstitutionId, @RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        if(profInstitutionId == null){
            return new ArrayList<>();
        }
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteProfInstitutionFormerNames(profInstitutionId, name, page, pageSize);
        return result;
    }

    @GetMapping("/certificate-prof-qualifications")
    public List<BaseAutocompleteDTO> autocompleteCertificateProfQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteCertificateProfQualifications(name, page, pageSize);
        return result;
    }

    @GetMapping("/original-edu-levels")
    public List<BaseAutocompleteDTO> autocompleteOriginalEduLevels(@RequestParam String name,
                                                          @RequestParam(required = false) Integer page,
                                                          @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteOriginalEduLevels(name, page, pageSize);
        return result;
    }

    @GetMapping("/original-edu-levels-translated")
    public List<BaseAutocompleteDTO> autocompleteOriginalEduLevelsTranslated(@RequestParam String name,
                                                                    @RequestParam(required = false) Integer page,
                                                                    @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteOriginalEduLevelsTranslated(name, page, pageSize);
        return result;
    }

    @GetMapping("/profession-names")
    public List<BaseAutocompleteDTO> autocompleteProfessionNames(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        List<BaseAutocompleteDTO> result = boPublicServicesService.autocompleteProfessionNames(name, page, pageSize);
        return result;
    }
}
