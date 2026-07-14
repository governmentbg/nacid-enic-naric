package bg.duosoft.nacidfrontofficedto.qualassess.filter;

import bg.duosoft.nacidfrontofficedto.BaseFilterDTO;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 07.10.2024
 * Time: 15:21
 */
@Data
public class OriginalSpecialityAutocompleteFilterDTO extends BaseFilterDTO {
    private String countryCode;
    private String originalSpecialityName;
}
