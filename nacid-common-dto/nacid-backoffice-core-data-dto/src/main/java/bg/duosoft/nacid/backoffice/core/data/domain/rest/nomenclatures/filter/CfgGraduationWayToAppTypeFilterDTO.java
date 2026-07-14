package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
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
public class CfgGraduationWayToAppTypeFilterDTO extends BaseFilterDTO {
    private String graduationWay;
    private String applicationType;
    private String applicationSubType;
    private String orderBy = "graduationWayName";
}
