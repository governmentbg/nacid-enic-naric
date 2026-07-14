package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response;

import lombok.Data;

@Data
public class EqualizationSubjectDto {
    private String subjectName;
    private String subjectGrade;
    private Double subjectGradeBg;
    private String subjectGradeBgText;
}
