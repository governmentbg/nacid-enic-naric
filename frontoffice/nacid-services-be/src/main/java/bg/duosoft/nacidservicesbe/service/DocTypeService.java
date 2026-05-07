package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeRequirementDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 17:03
 */
public interface DocTypeService {

    List<DocTypeDTO> getApplicationDocTypes(CommonApplicationDTO application);
    List<CfgDocTypeRequirementDTO> getApplicationDocTypeRequirements(CommonApplicationDTO application);
}
