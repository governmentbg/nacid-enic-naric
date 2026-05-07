package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.09.2022
 * Time: 13:46
 */
@Data
@NoArgsConstructor
public class GraduationDocumentTypeDTO extends IntegerKeyNomenclatureBase {
    private List<CfgGraduationDocumentTypeConfigDTO> configs;

    public GraduationDocumentTypeDTO(Integer id) {
        this.id = id;
    }
}
