package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompetentInstitutionDTO extends IntegerKeyNomenclatureBase {
    private CountryDTO country;
    private String originalName;
    private String url;
    private String notes;
    private AddressDTO address;

    public CompetentInstitutionDTO(Integer id) {
        this.id = id;
    }
}
