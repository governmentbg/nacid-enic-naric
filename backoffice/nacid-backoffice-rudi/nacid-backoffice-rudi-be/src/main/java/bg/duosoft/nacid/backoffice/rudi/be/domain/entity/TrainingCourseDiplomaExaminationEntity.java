package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_course_diploma_examination", schema = "rudi")
public class TrainingCourseDiplomaExaminationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "examination_date")
    private LocalDate examinationDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "authentic_flag")
    private Integer authenticFlag;

    @Column(name = "institution_communicated_flag")
    private Integer institutionCommunicatedFlag;

    @Column(name = "university_communicated_flag")
    private Integer universityCommunicatedFlag;

    @Column(name = "found_in_register_flag")
    private Integer foundInRegisterFlag;

    @Column(name = "state_approved_flag")
    private Integer stateApprovedFlag;

    @ManyToOne
    @JoinColumn(name = "competent_institution_id", referencedColumnName = "id")
    private CompetentInstitutionEntity competentInstitution;

    @OneToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id")
    private TrainingCourseEntity trainingCourse;
}
