package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleItemDTO extends IntegerKeyNomenclatureBase {
    private String qualificationLevelLabel;

    public ArticleItemDTO(Integer id) {
        this.id = id;
    }
}
