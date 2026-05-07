package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 11:03
 */
@Entity
@Table(name = "regprof_training_course", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofTrainingCourseEntity implements Serializable {

    @Id
    @Column(name = "rte_id", updatable = false)
    private Integer trainingCourseId;

    @OneToOne
    @JoinColumn(name = "rte_id", referencedColumnName = "id")
    @MapsId
    private RegprofTrainingExperienceEntity trainingExperience;

    @Column(name = "education_type")
    private String educationTypeCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RegprofTrainingCourseSpecialitiesEntity> allSpecialities;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private RegprofHigherTrainingCourseEntity higherTrainingCourse;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private RegprofPostgraduateTrainingCourseEntity postgraduateTrainingCourse;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private RegprofSecondaryTrainingCourseEntity secondaryTrainingCourse;
}
