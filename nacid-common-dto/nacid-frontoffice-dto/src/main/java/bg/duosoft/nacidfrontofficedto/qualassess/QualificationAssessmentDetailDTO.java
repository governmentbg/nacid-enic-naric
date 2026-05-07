package bg.duosoft.nacidfrontofficedto.qualassess;

import lombok.Data;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 02.10.2024
 * Time: 15:54
 */
@Data
public class QualificationAssessmentDetailDTO implements Serializable {
    private Integer id;
    private String universityName;
    private String europeanQualificationFrameworkName;
    private String nationalQualificationFrameworkName;
    private String bolognaCycleName;
    private Integer applicationId;
    private String originalSpecialityName;

}
