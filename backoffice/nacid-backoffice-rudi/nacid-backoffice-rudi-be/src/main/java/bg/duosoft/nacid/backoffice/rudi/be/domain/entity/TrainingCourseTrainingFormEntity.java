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

@Entity
@Table(name = "training_course_training_form", schema = "rudi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable(value = false)
public class TrainingCourseTrainingFormEntity implements Serializable {

    @Id
    @Column(name = "tce_id")
    private Integer id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id")
    private TrainingCourseEntity trainingCourse;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'TRAINING_FORM'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "tfm_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity trainingForm;

    @Column(name = "notes")
    private String notes;

}
