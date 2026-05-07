package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceListFilterDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 15:07
 */
public interface ApplicationCorrespondenceService {

    List<ApplicationCorrespondenceDTO> getCorrespondenceForApplication(Integer applicationId);
    List<ApplicationCorrespondenceDTO> filterAllCorrespondence(ApplicationCorrespondenceListFilterDTO filter);
    Integer getTotalCorrespondenceCount(ApplicationCorrespondenceListFilterDTO filter);

    ApplicationCorrespondenceDTO getCorrespondence(Integer id);
    ApplicationCorrespondenceDTO createCorrespondence(ApplicationCorrespondenceDTO correspondence);
    ApplicationCorrespondenceDTO readCorrespondence(Integer id);
}
