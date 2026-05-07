package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.NacidUserAutocompleteDTO;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationsService {

    ApplicationDTO getApplicationById(Integer id);

    List<NacidUserAutocompleteDTO> getCreatedUsersAutocomplete(String applicationType);

    List<NacidUserAutocompleteDTO> getResponsibleUsersAutocomplete(String applicationType);

    Pair<String, String> getAppTypeAndSubtypeById(Integer id);

    LocalDateTime getDateCreated(Integer id);

    List<ApplicationTableViewDTO> getApplicationsByAddressIdAndType(String addressType, Integer addressId);

    Integer getApplicationsCountByAddressIdAndType(String addressType, Integer addressId);

    List<AppPersonDataDTO> getApplicationsByPersonId(Integer personId);

    Integer getApplicationsCountByPersonId(Integer personId);

    String selectStatusCodeById(Integer applicationId);

    Integer selectEfilingIdByApplicationId(Integer id);

    Integer selectApplicationIdByEfilingId(Integer efilingId);

    String getStatusCodeByEntryDetails(String entryNumber, LocalDate entryDate);
    Integer getApplicationIdByEntryDetails(String entryNumber, LocalDate entryDate);

    ApplicationBaseDataDTO getApplicationBaseData(String entryNumber, LocalDate entryDate);

    ApplicationBaseDataDTO getApplicationBaseDataByAbdocsId(Integer abdocsId);

    String getApplicationResponsibleUserByBackofficeNumber(String backofficeNumber);

    void updateApplicationPaidFlag(String entryNum, LocalDate entryDate, Integer paidFlag);

}
