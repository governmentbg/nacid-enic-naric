package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.AcceptApplicationRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApostilleApplicationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.06.2023
 * Time: 14:35
 */
public interface RegprofApostilleService {

    RegprofApostilleApplicationDTO createRegprofApostilleApplication(RegprofApostilleApplicationDTO application);
    void acceptRegprofApostilleApplication(AcceptApplicationRequestDTO acceptApplicationRequestDTO);
    byte[] regenerateReceipt(Integer id, boolean addToApplication, boolean keepOldReceipt);
}
