package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 10.09.2025
 * Time: 15:15
 */
public interface CopyTypeService {
    List<ReferenceDataDTO> getAll(ApplicationType applicationType, ApplicationSubtype applicationSubtype, boolean onlyActive);
}
