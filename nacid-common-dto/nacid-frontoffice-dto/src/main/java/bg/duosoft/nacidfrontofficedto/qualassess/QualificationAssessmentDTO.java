package bg.duosoft.nacidfrontofficedto.qualassess;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import lombok.Data;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 03.10.2024
 * Time: 11:30
 */
@Data
public class QualificationAssessmentDTO {
    private Integer id;
    private ReferenceDataDTO recognizedEduLevel;
    private String originalEduLevelName;
    private CountryDTO country;
    private List<QualificationAssessmentDetailDTO> qualificationAssessmentDetails;
}
