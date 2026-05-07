package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfInstitutionDTO extends IntegerKeyNomenclatureBase {
    private CountryDTO country;
    private String webSite;
    private String urlDiplomaRegister;
    private AddressDTO address;
    private List<ProfInstitutionFormerNameDTO> formerNames;
    private List<ProfInstitutionEducationTypeDTO> educationTypes;

    public ProfInstitutionDTO(Integer id) {
        this.id = id;
    }
}
