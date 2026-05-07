package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgServiceTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:23
 */
public interface CfgServiceTypeService {

    List<CfgServiceTypeDTO> getServiceTypesConfigs();
    List<CfgServiceTypeDTO> getServiceTypesConfigsByApplicationTypeSubtype(ApplicationType applicantType, ApplicationSubtype applicationSubtype, boolean onlyActive);
}
