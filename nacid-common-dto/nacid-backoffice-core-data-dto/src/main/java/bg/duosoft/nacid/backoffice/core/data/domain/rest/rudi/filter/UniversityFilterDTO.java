package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter;

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
public class UniversityFilterDTO extends BaseFilterDTO {
    private String bgName;
    private String orgName;
    private String countryCode;
    private Boolean isActive;
    private String orderBy = NomenclatureSortFields.BG_NAME;
}
