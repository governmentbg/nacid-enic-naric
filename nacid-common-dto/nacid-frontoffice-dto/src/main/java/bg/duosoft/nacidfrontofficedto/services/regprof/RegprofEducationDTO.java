package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:52
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofEducationDTO {

    private EducationType kind;
    private RegprofEducationEntryDTO educationEntrySecondary;
    private RegprofEducationEntryDTO educationEntryHigher;
    private RegprofEducationEntryDTO educationEntryADQ;
}
