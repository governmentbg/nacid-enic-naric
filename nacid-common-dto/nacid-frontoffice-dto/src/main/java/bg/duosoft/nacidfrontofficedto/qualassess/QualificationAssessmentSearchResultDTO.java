package bg.duosoft.nacidfrontofficedto.qualassess;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 03.10.2024
 * Time: 16:42
 */
@Data
public class QualificationAssessmentSearchResultDTO {
    private Integer id;
    private String recognizedEduLevelName;
    private String originalEduLevelName;
    private String countryName;
    private Integer recordsCount;
}
