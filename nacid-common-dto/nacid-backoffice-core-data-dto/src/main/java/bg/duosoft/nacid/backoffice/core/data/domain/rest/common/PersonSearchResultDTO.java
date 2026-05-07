package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdNameDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonSearchResultDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String civilId;
    private StringIdNameDTO civilIdType;
    private String legalName;
    private StringIdNameDTO legalType;
    private StringIdNameDTO legalNatureType;
    private StringIdNameDTO originCountry;
    private StringIdNameDTO citizenship;
    private String email;
    private String originSettlementName;
    private String originCity;
    private Boolean active;
}
