package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SECommissionMemberFilterDTO extends BaseFilterDTO {
    private String username;
    private String firstName;
    private String lastName;
    private List<String> excludedMembers;
    private String orderBy = NomenclatureSortFields.FIRST_NAME;

}
