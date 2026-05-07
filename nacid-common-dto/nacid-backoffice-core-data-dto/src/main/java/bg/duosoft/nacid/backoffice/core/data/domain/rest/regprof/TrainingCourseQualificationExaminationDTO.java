package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleDirectiveDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleItemDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCourseQualificationExaminationDTO {
    private Integer id;
    private ReferenceDataDTO recognizedEducationLevel;
    private ReferenceDataDTO recognizedQualificationDegree;
    private ArticleItemDTO articleItem;
    private ArticleDirectiveDTO articleDirective;
    private String recognizedProfession;
    private String recProfQualificationModules;
    private Boolean isRecognizedQualificationTeacher;
    private List<TrainingCourseQualificationExaminationTeacherDetailDTO> teacherDetails;
}
