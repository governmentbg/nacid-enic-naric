package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfInstitutionFilterDTO extends BaseNomenclatureFilterDTO<Integer> {
    private String educationType;
    private String countryCode;
    private String orderBy = NomenclatureSortFields.ID;
}
