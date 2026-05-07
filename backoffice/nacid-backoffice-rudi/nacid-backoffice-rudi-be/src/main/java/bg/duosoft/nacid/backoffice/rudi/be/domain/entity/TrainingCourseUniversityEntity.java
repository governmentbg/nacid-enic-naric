package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_course_universities", schema = "rudi")
public class TrainingCourseUniversityEntity {
    @EmbeddedId
    private TrainingCourseUniversityEntityPK pk;

    @Column(name = "university_name_translated")
    private String universityNameTranslated;

    @Column(name = "university_contact")
    private String universityContact;

    @Column(name = "ord_num")
    private Integer ordNum;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private FacultyEntity faculty;
}
