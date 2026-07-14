package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_course_university_examination", schema = "rudi")
public class TrainingCourseUniversityExaminationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id")
    private TrainingCourseEntity trainingCourse;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "uny_id", referencedColumnName = "id")
    private UniversityEntity university;

    @Column(name = "examination_date")
    private LocalDate examinationDate;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "communicated_flag")
    private Integer communicatedFlag;

    @Column(name = "recognized_flag")
    private Integer recognizedFlag;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'UNI_EXAM_TRAINING_LOCATION'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "training_location_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity trainingLocation;

    @Column(name = "joint_degree_flag")
    private Integer jointDegreeFlag;

    @ManyToMany
    @JoinTable(
            schema = "rudi",
            name = "training_course_university_examination_competent_institutions",
            joinColumns = @JoinColumn(name = "training_course_university_examination_id"),
            inverseJoinColumns = @JoinColumn(name = "competent_institution_id"))
    Set<CompetentInstitutionEntity> competentInstitutions;

    @OneToMany(mappedBy = "universityExamination", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UniversityExaminationTrainingFormEntity> universityExaminationTrainingForms;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            schema = "rudi",
            name = "training_course_university_examination_attached_docs",
            joinColumns = @JoinColumn(name = "training_course_university_examination_id"),
            inverseJoinColumns = @JoinColumn(name = "application_attached_docs_id"))
    List<ApplicationAttachedDocEntity> attachedDocs;
}
