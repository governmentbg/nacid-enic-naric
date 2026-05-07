package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "training_course_program_examination", schema = "rudi")
public class TrainingCourseProgramExaminationEntity implements Serializable {
    @Id
    @Column(name = "tce_id")
    private Integer id;

    @Column(name = "legitimate_flag")
    private Integer legitimateFlag;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'TRAINING_PROGRAM_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "training_program_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity programType;

    @OneToOne
    @MapsId
    @JoinColumn(name = "tce_id", referencedColumnName = "id")
    private TrainingCourseEntity trainingCourse;
}
