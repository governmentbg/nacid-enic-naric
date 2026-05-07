package bg.duosoft.nacidfrontofficedto.nomenclature.filter;

import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.NomenclatureSortFields;
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
public class NationalUniversityDataFilterDTO extends BaseNomenclatureFilterDTO<String> {

    private String settlementCode;
    private String address;
    private String zipCode;
    private String website;
}
