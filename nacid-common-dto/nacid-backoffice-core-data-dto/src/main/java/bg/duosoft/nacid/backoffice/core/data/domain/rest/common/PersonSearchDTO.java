package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonSearchDTO {
    private String personType;
    private String name;
    private String civilId;
    private String civilIdType;
    private String humanitarianStatus;
    private StringIdDTO citizenship;
    private StringIdDTO originCountry;
    private String originCity;
    private String email;
    private String phone;
    private Boolean onlyActive;
}
