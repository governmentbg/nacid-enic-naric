package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonFormRequestDTO extends PersonDTO {
    private Boolean createNewPersonVersionFlag;

}
