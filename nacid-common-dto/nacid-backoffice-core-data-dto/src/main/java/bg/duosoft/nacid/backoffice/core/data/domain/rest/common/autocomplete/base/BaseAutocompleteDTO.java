package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseAutocompleteDTO<T> {
    private T id;
    private String name;
    private Boolean isActive;
}
