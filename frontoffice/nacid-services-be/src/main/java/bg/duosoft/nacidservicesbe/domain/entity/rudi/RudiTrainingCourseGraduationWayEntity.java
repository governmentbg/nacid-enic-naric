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
 * Time: 14:24
 */
@Entity
@Table(name = "rudi_training_course_graduation_way", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiTrainingCourseGraduationWayEntity implements Serializable, RudiTrainingCourseRelated {

    @EmbeddedId
    private RudiTrainingCourseIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    @MapsId("trainingCourseId")
    private RudiTrainingCourseEntity trainingCourse;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'GRADUATION_WAY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "graduation_way_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity graduationWay;

    @Column(name = "notes")
    private String notes;
}
