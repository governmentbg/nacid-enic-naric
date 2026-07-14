package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingInstitutionDTO extends IntegerKeyNomenclatureBase {
    private CountryDTO country;
    private AddressDTO address;
    private String webSite;
    private List<UniversityDTO> universities;

    public TrainingInstitutionDTO(Integer id) {
        this.id = id;
    }
}
