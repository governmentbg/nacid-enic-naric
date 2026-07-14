package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidbackofficepublicservicesclient.client.*;
import bg.duosoft.nacidbackofficepublicservicesclient.client.regprof.BOCertificateProfQualificationClient;
import bg.duosoft.nacidbackofficepublicservicesclient.client.regprof.BOProfessionNameClient;
import bg.duosoft.nacidbackofficepublicservicesclient.client.regprof.BOProfessionalInstitutionClient;
import bg.duosoft.nacidbackofficepublicservicesclient.client.rudi.BOUniversityClient;
import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.person.PersonalNacidIdentifierDTO;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesService;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 13:38
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BoPublicServicesServiceImpl implements BoPublicServicesService {

    private final BOPersonalNacidIdClient boPersonalNacidIdClient;
    private final BOUniversityClient boUniversityClient;
    private final BOSpecialityClient boSpecialityClient;
    private final BOQualificationClient boQualificationClient;
    private final BOHigherQualificationClient boHigherQualificationClient;
    private final BOHigherSpecialityClient boHigherSpecialityClient;
    private final BOSdkQualificationClient boSdkQualificationClient;
    private final BOSdkSpecialityClient boSdkSpecialityClient;
    private final BOSecondaryQualificationClient boSecondaryQualificationClient;
    private final BOSecondarySpecialityClient boSecondarySpecialityClient;
    private final BOProfessionalInstitutionClient boProfessionalInstitutionClient;
    private final BOCertificateProfQualificationClient boCertificateProfQualificationClient;
    private final BOApplicationClient boApplicationClient;
    private final BOOriginalEduLevelClient boOriginalEduLevelClient;
    private final BOOriginalSpecialityClient boOriginalSpecialityClient;
    private final BOOriginalQualificationClient boOriginalQualificationClient;
    private final BOProfessionNameClient boProfessionNameClient;

    @Override
    public String generatePersonalNacidIdentifier() {
        PersonalNacidIdentifierDTO id = boPersonalNacidIdClient.generate(SecurityUtils.getUsername());
        return id.getValue();
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteUniversities(String name, Integer page, Integer pageSize) {
        try {
            return boUniversityClient.searchForAutocomplete(name, true, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boUniversityClient.searchForAutocomplete(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteUniversityFaculties(Integer universityId, String name, Integer page, Integer pageSize) {
        try {
            return boUniversityClient.searchFacultyForAutocomplete(universityId, name, true, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boUniversityClient.searchFacultyForAutocomplete(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteSpecialities(String name, Integer page, Integer pageSize) {
        try {
            return boSpecialityClient.autocompleteSpecialities(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boSpecialityClient.autocompleteSpecialities(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteOriginalSpecialities(String name, Integer page, Integer pageSize) {
        try {
            return boOriginalSpecialityClient.autocompleteOriginalSpecialities(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boOriginalSpecialityClient.autocompleteOriginalSpecialities(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteQualifications(String name, Integer page, Integer pageSize) {
        try {
            return boQualificationClient.autocompleteQualifications(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boSpecialityClient.autocompleteQualifications(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteOriginalQualifications(String name, Integer page, Integer pageSize) {
        try {
            return boOriginalQualificationClient.autocompleteOriginalQualifications(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boOriginalQualificationClient.autocompleteOriginalQualifications(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteHigherSpecialities(String name, Integer page, Integer pageSize) {
        try {
            return boHigherSpecialityClient.autocompleteHigherSpecialities(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boHigherSpecialityClient.autocompleteHigherSpecialities(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteHigherQualifications(String name, Integer page, Integer pageSize) {
        try {
            return boHigherQualificationClient.autocompleteHigherQualification(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boHigherQualificationClient.autocompleteHigherQualification(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteSdkSpecialities(String name, Integer page, Integer pageSize) {
        try {
            return boSdkSpecialityClient.autocompleteSdkSpecialities(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boSdkSpecialityClient.autocompleteSdkSpecialities(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteSdkQualifications(String name, Integer page, Integer pageSize) {
        try {
            return boSdkQualificationClient.autocompleteSdkQualification(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boSdkQualificationClient.autocompleteSdkQualification(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteSecondarySpecialities(Integer qualificationId, String name, Integer page, Integer pageSize) {
        try {
            return boSecondarySpecialityClient.autocompleteSecondarySpecialities(qualificationId, true, name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boSecondarySpecialityClient.autocompleteSecondarySpecialities(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteSecondaryQualifications(String name, Integer page, Integer pageSize) {
        try {
            return boSecondaryQualificationClient.autocompleteSecondaryQualification(true, name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boSecondaryQualificationClient.autocompleteSecondaryQualification(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteProfInstitutions(EducationType educationType, String name, Integer page, Integer pageSize) {
        try {
            return boProfessionalInstitutionClient.selectForAutocomplete(educationType, name, true, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boSecondaryQualificationClient.autocompleteProfInstitutions(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteProfInstitutionFormerNames(Integer profInstitutionId, String name, Integer page, Integer pageSize) {
        try {
            return boProfessionalInstitutionClient.selectFormerNamesForAutocomplete(profInstitutionId, name, true, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boSecondaryQualificationClient.autocompleteProfInstitutionFormerNames(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteCertificateProfQualifications(String name, Integer page, Integer pageSize) {
        try {
            return boCertificateProfQualificationClient.autocompleteCertificateProfQualifications(name, page, pageSize) ;
        } catch (Exception e){
            log.error("Failed to call boCertificateProfQualificationClient.autocompleteCertificateProfQualifications(): {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteOriginalEduLevels(String name, Integer page, Integer pageSize) {
        try {
            return boOriginalEduLevelClient.autocompleteOriginalEduLevels(name, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boOriginalEduLevelClient.autocompleteOriginalEduLevels(): {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteOriginalEduLevelsTranslated(String name, Integer page, Integer pageSize) {
        try {
            return boOriginalEduLevelClient.autocompleteOriginalEduLevelsTranslated(name, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boOriginalEduLevelClient.autocompleteOriginalEduLevelTranslated(): {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean applicationNotDeniedByEntryDetails(String entryNumber, LocalDate entryDate) {
        try {
            return boApplicationClient.appNotDeniedForEntryDetails(entryNumber, entryDate) ;
        } catch (Exception e){
            log.error("Failed to call boApplicationClient.appNotDeniedForEntryDetails(): {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<BaseAutocompleteDTO> autocompleteProfessionNames(String name, Integer page, Integer pageSize) {
        try {
            return boProfessionNameClient.autocompleteProfessionNames(name, page, pageSize);
        } catch (Exception e){
            log.error("Failed to call boProfessionNameClient.autocompleteProfessionNames: {}", e.getMessage());
            return null;
        }
    }

}
