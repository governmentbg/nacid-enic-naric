package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 15:23
 */
@Entity
@Table(name = "rudi_training_course_university", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiTrainingCourseUniversityEntity implements Serializable, RudiTrainingCourseRelated {

    @EmbeddedId
    private RudiTrainingCourseIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    @MapsId("trainingCourseId")
    private RudiTrainingCourseEntity trainingCourse;

    @Column(name = "uny_id")
    private Integer uniId;

    @Column(name = "ord_num")
    private Integer ordNum;

    @Column(name = "faculty_id")
    private Integer facultyId;

    @Column(name = "uny_name")
    private String uniName;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "university_contact")
    private String universityContact;
}
