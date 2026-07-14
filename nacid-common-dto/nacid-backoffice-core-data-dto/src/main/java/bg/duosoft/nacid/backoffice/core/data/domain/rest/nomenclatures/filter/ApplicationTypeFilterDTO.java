package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationTypeFilterDTO extends BaseNomenclatureFilterDTO<String> {
    private String orderBy = NomenclatureSortFields.ID;

    @Override
    public String getId() {
        return super.getId();
    }
}