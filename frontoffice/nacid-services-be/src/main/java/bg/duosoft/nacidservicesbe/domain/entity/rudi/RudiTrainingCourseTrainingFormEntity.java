package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 15:12
 */
@Entity
@Table(name = "rudi_training_course_training_form", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiTrainingCourseTrainingFormEntity implements Serializable, RudiTrainingCourseRelated {

    @EmbeddedId
    private RudiTrainingCourseIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    @MapsId("trainingCourseId")
    private RudiTrainingCourseEntity trainingCourse;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'TRAINING_FORM'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "tfm_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity trainingForm;

    @Column(name = "notes")
    private String notes;
}
