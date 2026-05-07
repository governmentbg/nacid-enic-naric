package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgEduLevelDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:22
 */
public interface CfgEduLevelService {

    List<CfgEduLevelDTO> getEduLevelsConfigs();
    List<CfgEduLevelDTO> getEduLevelsConfigsByApplicationTypeSubtype(ApplicationType applicationType, ApplicationSubtype applicationSubtype);
}
