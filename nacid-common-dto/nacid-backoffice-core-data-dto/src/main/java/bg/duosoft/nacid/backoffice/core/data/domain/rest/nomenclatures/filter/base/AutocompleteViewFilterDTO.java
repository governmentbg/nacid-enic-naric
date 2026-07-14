package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Pageable;
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
public class AutocompleteViewFilterDTO implements Pageable {
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
    private String name;

}
