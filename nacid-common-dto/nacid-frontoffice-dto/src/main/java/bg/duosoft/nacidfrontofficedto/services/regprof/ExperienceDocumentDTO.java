package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.ProfExperienceDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 13:31
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDocumentDTO {

    private ProfExperienceDocTypeDTO type;
    private String documentNumber;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate documentDate;
    private String institutionName;
    private List<WorkPeriodDTO> workPeriods;
}
