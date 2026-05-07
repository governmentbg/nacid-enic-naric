package bg.duosoft.nacidfrontofficedto.services.regprof;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:53
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofExperienceDTO {

    private String profession;
    private List<ExperienceDocumentDTO> experienceDocuments;
}
