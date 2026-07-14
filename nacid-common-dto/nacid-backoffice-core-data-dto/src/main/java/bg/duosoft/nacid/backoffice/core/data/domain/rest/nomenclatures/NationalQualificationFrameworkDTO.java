package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NationalQualificationFrameworkDTO extends IntegerKeyNomenclatureBase {
    private CountryDTO country;
    public NationalQualificationFrameworkDTO(Integer id) {
        this.id = id;
    }
    public NationalQualificationFrameworkDTO(Integer id, String name, Boolean isActive, CountryDTO country) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.country = country;
    }
}
