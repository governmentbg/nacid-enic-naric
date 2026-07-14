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
@Table(name = "training_course_graduation_way", schema = "rudi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable(value = false)
public class TrainingCourseGraduationWayEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    private TrainingCourseEntity trainingCourse;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'GRADUATION_WAY'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "graduation_way_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity graduationWay;

    @Column(name = "notes")
    private String notes;

}
