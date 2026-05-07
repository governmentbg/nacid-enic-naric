package bg.duosoft.nacidfrontofficedto.qualassess.filter;

import bg.duosoft.nacidfrontofficedto.BaseFilterDTO;
import bg.duosoft.nacidfrontofficedto.Pageable;
import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidfrontofficedto.utils.constants.RoleSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 03.10.2024
 * Time: 12:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QualificationAssessmentFilterDTO extends BaseFilterDTO {
    private String countryCode;
    private String recognizedEduLevelCode;
    private String originalEduLevelName;
    private Boolean originalEduLevelExactMatch;
    private String originalSpecialityName;
    private Boolean originalSpecialityExactMatch;
    private String universityName;
    private Boolean universityExactMatch;
}
