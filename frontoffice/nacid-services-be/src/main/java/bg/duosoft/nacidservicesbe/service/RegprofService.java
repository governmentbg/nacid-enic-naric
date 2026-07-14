package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 18:09
 */
public interface RegprofService extends BaseApplicationService<RegprofApplicationDTO, RegprofApplicantDetailsDTO, RegprofEducationDetailsDTO> {

    Map<String, String> createRegprofFeeCalculationParamsMap(String serviceType);
}
