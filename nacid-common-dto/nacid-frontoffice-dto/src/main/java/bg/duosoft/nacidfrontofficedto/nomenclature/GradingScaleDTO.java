package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import bg.duosoft.nacidfrontofficedto.services.common.application.ScaleTypeEnum;
import lombok.Data;

@Data
public class GradingScaleDTO extends NomenclatureBaseImpl<Integer> {
    private ScaleTypeEnum scaleType;
    private String description;
    private CountryDTO country;
}
