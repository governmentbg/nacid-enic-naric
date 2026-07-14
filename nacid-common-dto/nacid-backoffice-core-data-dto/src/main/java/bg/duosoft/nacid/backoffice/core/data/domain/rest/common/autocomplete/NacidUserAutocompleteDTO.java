package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "newInstance")
public class NacidUserAutocompleteDTO {
    private String username;
    private String fullName;
    private Boolean isActive;
}
