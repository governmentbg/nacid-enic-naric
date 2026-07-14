package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationConfigDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 17:00
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class DocTypeDTO extends NomenclatureBaseImpl<Integer> {
    private String validationFileGroup;

}
