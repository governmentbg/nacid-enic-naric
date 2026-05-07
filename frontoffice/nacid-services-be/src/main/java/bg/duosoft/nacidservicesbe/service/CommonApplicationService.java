package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 15:28
 */
public interface CommonApplicationService {

    List<ApplicationListRecordDTO> getAllApplications(ApplicationListFilterDTO applicationListFilter);
    ApplicationListRecordDTO getApplicationById(Integer id);
    Integer getTotalApplications(ApplicationListFilterDTO applicationListFilter);
    ApplicationSubtype getApplicationSubtype(String dossierNumber, String accessCode);
    ApplicationSubtype getApplicationSubtype(Integer id);
    String getApplicationTempNumber(Integer id);
    String getApplicationUserCreated(Integer id);
    boolean applicationHasFoStatus(Integer id, FoApplicationStatus foApplicationStatus);
    FoApplicationStatus getFoStatus(Integer id);
    boolean userIsOwner(Integer id, String username);
    ApplicationDetailsForSignDTO getApplicationSignDetails(Integer id);
    LocalDate getApplicationDateCreated(Integer id);
    List<String> getAllLastStatusesByUser(String user);
    List<ApplicationMultipleRecordDTO> getRelatedAppsFromMultiple(Integer singleApplicationId);
    void changePaidFlag(String tempNumber, Boolean paid);
    byte[] getAcceptedReceipt(Integer id);
    boolean applicationCanBeDeleted(Integer id);
    Integer getApplicationIdForDossierNumberAccessCode(String dossierNumber, String accessCode);
}
