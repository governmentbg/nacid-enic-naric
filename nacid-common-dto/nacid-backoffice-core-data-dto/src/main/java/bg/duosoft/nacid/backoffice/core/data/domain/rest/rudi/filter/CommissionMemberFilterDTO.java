package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter;

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
public class CommissionMemberFilterDTO extends BaseFilterDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer profGroup;
    private String commissionPosition;
    private Boolean isActive;
    private List<Integer> excludedMembers;
    private String fullName;
    private String orderBy = NomenclatureSortFields.ID;

}
