package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationConfigDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:37
 */
@Data
@NoArgsConstructor
public class LanguageDTO extends NomenclatureBaseImpl<String> {

    private List<ApplicationConfigDTO> applicationConfigs;
}
