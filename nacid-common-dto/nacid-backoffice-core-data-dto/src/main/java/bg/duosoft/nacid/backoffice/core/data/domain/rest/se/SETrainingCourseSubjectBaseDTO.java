package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradeEquivalenceDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class SETrainingCourseSubjectBaseDTO implements Serializable {
    private Integer id;
    private String subjectName;
    private String translatedSubjectName;
    private String diplomaGrade;
    private GradeEquivalenceDTO gradeEquivalence;
    private BigDecimal bulgarianGrade;
    private String bulgarianGradeText;
    private Integer minFormulaValue;
    private Integer maxFormulaValue;
}
