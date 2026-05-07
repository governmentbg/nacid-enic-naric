package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 15:09
 */
@Entity
@Table(name = "rudi_training_course_speciality", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiTrainingCourseSpecialityEntity implements Serializable, RudiTrainingCourseRelated {

    @EmbeddedId
    private RudiTrainingCourseIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    @MapsId("trainingCourseId")
    private RudiTrainingCourseEntity trainingCourse;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "original_speciality")
    private String originalSpeciality;

}
