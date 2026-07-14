package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 11:50
 */
public interface ServicesApplicationCorrespondenceBaseClient {

    @PostMapping("/create")
    ApplicationCorrespondenceDTO createApplicationCorrespondence(ApplicationCorrespondenceDTO applicationCorrespondenceDTO);
}
