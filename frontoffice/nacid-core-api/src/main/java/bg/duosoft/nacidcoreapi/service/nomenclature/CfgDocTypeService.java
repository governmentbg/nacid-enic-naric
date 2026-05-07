package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 16:17
 */
public interface CfgDocTypeService {

    List<CfgDocTypeDTO> getDocTypesConfigsByAppTypeAndSubtype(ApplicationType applicationType, ApplicationSubtype applicationSubtype);
}
