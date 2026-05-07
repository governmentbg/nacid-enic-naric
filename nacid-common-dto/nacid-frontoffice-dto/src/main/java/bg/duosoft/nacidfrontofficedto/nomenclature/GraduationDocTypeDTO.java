package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 17:40
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class GraduationDocTypeDTO extends NomenclatureBaseImpl<Integer> {

    private List<EducationType> educationTypes;
}
