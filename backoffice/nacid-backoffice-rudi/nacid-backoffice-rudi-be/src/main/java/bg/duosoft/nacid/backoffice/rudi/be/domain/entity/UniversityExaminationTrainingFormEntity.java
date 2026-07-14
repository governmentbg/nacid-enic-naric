package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_course_university_examination_training_forms", schema = "rudi")
public class UniversityExaminationTrainingFormEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "training_course_university_examination_id", referencedColumnName = "id")
    private TrainingCourseUniversityExaminationEntity universityExamination;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'TRAINING_FORM'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "training_form_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity trainingForm;

    @Column(name = "notes")
    private String notes;
}
