package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UniversitySimpleDTO {
    private Integer id;
    private String bgName;
    private String orgName;
    private CountryDTO country;
    private AddressDTO address;
}
