package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDirectiveDTO extends IntegerKeyNomenclatureBase {
    private List<ArticleItemDTO> items;
    public ArticleDirectiveDTO(Integer id) {
        super.id = id;
    }
}
