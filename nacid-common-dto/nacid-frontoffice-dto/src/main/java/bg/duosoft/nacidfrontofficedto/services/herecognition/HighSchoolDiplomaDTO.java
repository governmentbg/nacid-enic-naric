package bg.duosoft.nacidfrontofficedto.services.herecognition;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HighSchoolDiplomaDTO {

    private CountryDTO country;
    private String city;
    private String school;
    private String graduationYear;
    private String notes;
}
