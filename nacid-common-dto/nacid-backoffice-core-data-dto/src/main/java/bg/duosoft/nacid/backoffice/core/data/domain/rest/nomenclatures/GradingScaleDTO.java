package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GradingScaleDTO extends IntegerKeyNomenclatureBase {
    private ReferenceDataDTO scaleType;
    private String description;
    private CountryDTO country;
    private Integer startYear;
    private Integer endYear;
    private String alternateKey;

    public GradingScaleDTO(Integer id) {
        this.id = id;
    }
}
