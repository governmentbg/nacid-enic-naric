package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegalEntityFilterDTO extends BaseFilterDTO {
    private String legalName;
    private List<String> legalNatureTypes;
}
