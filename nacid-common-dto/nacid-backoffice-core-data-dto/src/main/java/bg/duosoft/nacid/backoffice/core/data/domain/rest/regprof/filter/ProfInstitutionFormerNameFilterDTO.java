package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
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
public class ProfInstitutionFormerNameFilterDTO extends BaseFilterDTO {
    private Integer id;
    private Integer profInstitutionId;
    private String formerName;
    private String orderBy = NomenclatureSortFields.ID;
}
