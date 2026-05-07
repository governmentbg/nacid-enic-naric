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
public class GradeEquivalenceFilterDTO extends BaseNomenclatureFilterDTO<Integer> {
    private String orderBy = NomenclatureSortFields.ID;
}