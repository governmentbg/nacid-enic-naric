package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 12:05
 */
@Entity
@Table(name = "regprof_training_course_specialities", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofTrainingCourseSpecialitiesEntity implements Serializable {

    @EmbeddedId
    private RegprofTrainingCourseSpecialitiesIdEntity id;

    @ManyToOne
    @JoinColumn(name = "rte_id", referencedColumnName = "rte_id", nullable = false)
    @MapsId("regprofTrainingCourseId")
    private RegprofTrainingCourseEntity trainingCourse;

    @Column(name = "secondary_speciality_id")
    private Integer secondarySpecialityId;

    @Column(name = "secondary_speciality")
    private String secondarySpeciality;

    @Column(name = "higher_speciality")
    private String higherSpeciality;

    @Column(name = "sdk_speciality")
    private String sdkSpeciality;
}
