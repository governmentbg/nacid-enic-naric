package bg.duosoft.nacidfrontofficedto.services.common.education;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
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
public class PreviousUniversityDiplomaDTO {
    private String universityName;
    private String universityNameId;
    private ReferenceDataDTO gainedLevel;
    private String speciality;
    private String graduationYear;
    private String notes;
}
