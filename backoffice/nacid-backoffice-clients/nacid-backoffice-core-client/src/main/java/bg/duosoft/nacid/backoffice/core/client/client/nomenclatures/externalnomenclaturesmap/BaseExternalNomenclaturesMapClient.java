package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.externalnomenclaturesmap;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface BaseExternalNomenclaturesMapClient {
    default String getExternalNomIdBySystemNomenclatureTypeInternalNomId(String system, String nomenclatureType, String internalNomId) {
        ExternalNomenclaturesMapDTO res = getSingleResultBySystemNomenclatureTypeInternalNomId(system, nomenclatureType, internalNomId);
        return res == null ? null : res.getExternalNomId();
    }
    default Integer getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(String system, String nomenclatureType, String internalNomId) {
        ExternalNomenclaturesMapDTO res = getSingleResultBySystemNomenclatureTypeInternalNomId(system, nomenclatureType, internalNomId);
        return res == null ? null : res.getExternalNomIdAsInteger();
    }
    private ExternalNomenclaturesMapDTO getSingleResultBySystemNomenclatureTypeInternalNomId(String system, String nomenclatureType, String internalNomId) {
        List<ExternalNomenclaturesMapDTO> res = getBySystemNomenclatureTypeInternalNomId(system, nomenclatureType, internalNomId);
        if (ObjectUtils.isEmpty(res)) {
            return null;
        } else if (res.size() > 1) {
            throw new IllegalArgumentException("There should be at most one config record for the given criteria System:" + system + " NomenclatureType: " + nomenclatureType + " internalNomId:" + internalNomId);
        } else {
            return res.get(0);
        }
    }
    @GetMapping("/by-system-nomenclature-type-internal-id")
    List<ExternalNomenclaturesMapDTO> getBySystemNomenclatureTypeInternalNomId(@RequestParam("system")String system, @RequestParam("nomenclatureType")String nomenclatureType, @RequestParam("internalNomId")String internalNomId);

    @GetMapping("/by-system-nomenclature-type")
    List<ExternalNomenclaturesMapDTO> getBySystemAndNomenclatureType(@RequestParam("system")String system, @RequestParam("nomenclatureType")String nomenclatureType);

    @PostMapping
    ExternalNomenclaturesMapDTO create(@RequestBody ExternalNomenclaturesMapDTO country);

    @PutMapping
    ExternalNomenclaturesMapDTO update(@RequestBody ExternalNomenclaturesMapDTO country);

    @DeleteMapping(value = "/")
    void deleteAll(@RequestParam(value = "system", required = false) String system);

}
