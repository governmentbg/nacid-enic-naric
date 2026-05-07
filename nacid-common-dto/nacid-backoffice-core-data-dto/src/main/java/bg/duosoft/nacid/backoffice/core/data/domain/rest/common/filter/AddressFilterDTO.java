package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
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
public class AddressFilterDTO extends BaseFilterDTO {
    private Integer id;
    private String contactPerson;
    private String email;
    private String address;
    private String postCode;
    private String phone;
    private String fax;
    private StringIdDTO country;
    private String city;
    private String postBox;
    private String addressType;
}
