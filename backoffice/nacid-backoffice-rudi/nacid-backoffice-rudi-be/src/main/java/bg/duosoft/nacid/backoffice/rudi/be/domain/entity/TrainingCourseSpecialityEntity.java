package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "training_course_speciality", schema = "rudi")
@Data
public class TrainingCourseSpecialityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    private TrainingCourseEntity trainingCourse;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "original_speciality")
    private String originalSpeciality;
}
