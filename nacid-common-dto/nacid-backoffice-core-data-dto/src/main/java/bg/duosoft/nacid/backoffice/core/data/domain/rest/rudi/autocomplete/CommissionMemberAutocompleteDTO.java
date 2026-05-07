package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.autocomplete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "newInstance")
public class CommissionMemberAutocompleteDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Boolean isActive;
}
