package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 13:38
 */
public interface BoPublicServicesService {

    String generatePersonalNacidIdentifier();
    List<BaseAutocompleteDTO> autocompleteUniversities(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteUniversityFaculties(Integer universityId, String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteSpecialities(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteOriginalSpecialities(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteQualifications(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteOriginalQualifications(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteHigherSpecialities(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteHigherQualifications(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteSdkSpecialities(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteSdkQualifications(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteSecondarySpecialities(Integer qualificationId, String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteSecondaryQualifications(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteProfInstitutions(EducationType educationType, String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteProfInstitutionFormerNames(Integer profInstitutionId, String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteCertificateProfQualifications(String name, Integer page, Integer pageSize);
    Boolean applicationNotDeniedByEntryDetails(String entryNumber, LocalDate entryDate);
    List<BaseAutocompleteDTO> autocompleteOriginalEduLevels(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteOriginalEduLevelsTranslated(String name, Integer page, Integer pageSize);
    List<BaseAutocompleteDTO> autocompleteProfessionNames(String name, Integer page, Integer pageSize);
}
