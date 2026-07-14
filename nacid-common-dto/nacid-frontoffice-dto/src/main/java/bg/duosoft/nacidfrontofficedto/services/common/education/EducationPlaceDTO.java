package bg.duosoft.nacidfrontofficedto.services.common.education;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:21
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EducationPlaceDTO {

    private Float key;
    private CountryDTO country;
    private String city;
}
