package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 25.08.2022
 * Time: 15:50
 */
@Entity
@Table(name = "training_course", schema = "rudi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable(value = false)
public class TrainingCourseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "diploma_num")
    private String diplomaNumber;

    @Column(name = "diploma_date")
    private LocalDate diplomaDate;

    @Column(name = "diploma_series")
    private String diplomaSeries;

    @Column(name = "diploma_registration_number")
    private String diplomaRegistrationNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private PersonEntity diplomaOwner;

    @Column(name = "owner_ean")
    private String diplomaOwnerEan;

    @OneToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id")
    private RudiApplicationEntity application;

    @OneToMany(mappedBy = "trainingCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingLocationEntity> trainingLocations;

    @ManyToOne
    @JoinColumn(name = "base_university_id", referencedColumnName = "id")
    private UniversityEntity baseUniversity;

    @ManyToOne
    @JoinColumn(name = "school_country", referencedColumnName = "code")
    private CountryEntity schoolCountry;

    @ManyToOne
    @JoinColumn(name = "school_settlement_code", referencedColumnName = "code")
    private EkSettlementEntity schoolSettlement;

    @Column(name = "school_city")
    private String schoolCity;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_graduation_date")
    private LocalDate schoolGraduationDate;

    @Column(name = "school_notes")
    private String schoolNotes;

    @ManyToOne
    @JoinColumn(name = "prev_diploma_university_id", referencedColumnName = "id")
    private UniversityEntity prevDiplomaUniversity;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'EDUCATION_LEVEL'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "prev_diploma_edu_level", referencedColumnName = "code"))
    })
    private ReferenceDataEntity prevDiplomaEduLevel;

    @Column(name = "prev_diploma_graduation_date")
    private LocalDate prevDiplomaGraduationDate;

    @Column(name = "prev_diploma_notes")
    private String prevDiplomaNotes;

    @Column(name = "prev_diploma_speciality")
    private String prevDiplomaSpeciality;

    @Column(name = "training_start")
    private LocalDate trainingStart;

    @Column(name = "training_end")
    private LocalDate trainingEnd;

    @Column(name = "credits")
    private Double credits;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "credit_hours")
    private Integer creditHours;

    @Column(name = "ects_credits")
    private Integer ectsCredits;

    @Column(name = "training_duration")
    private Double trainingDuration;

    @Column(name = "thesis_topic")
    private String thesisTopic;

    @Column(name = "thesis_topic_en")
    private String thesisTopicEn;

    @Column(name = "thesis_defence_date")
    private LocalDate thesisDefenceDate;

    @Column(name = "thesis_bibliography")
    private Integer thesisBibliography;

    @Column(name = "thesis_volume")
    private Integer thesisVolume;

    @Column(name = "thesis_annotation")
    private String thesisAnnotation;

    @Column(name = "thesis_annotation_en")
    private String thesisAnnotationEn;

    @Column(name = "scientific_supervisor")
    private String scientificSupervisor;

    @Column(name = "scientific_supervisor_en")
    private String scientificSupervisorEn;

    @Column(name = "reviewers")
    private String reviewers;

    @Column(name = "reviewers_en")
    private String reviewersEn;

    @Column(name = "jury_chair")
    private String juryChair;

    @Column(name = "jury_chair_en")
    private String juryChairEn;

    @Column(name = "jury_members")
    private String juryMembers;

    @Column(name = "jury_members_en")
    private String juryMembersEn;

    @ManyToOne
    @JoinColumn(name = "thesis_language_code", referencedColumnName = "code")
    private LanguageEntity thesisLanguage;

    @Column(name = "original_qualification")
    private String originalQualification;

    @Column(name = "original_edu_level_name")
    private String originalEduLevelName;

    @Column(name = "original_edu_level_translated")
    private String originalEduLevelTranslated;

    @ManyToOne
    @JoinColumn(name = "bologna_cycle_id", referencedColumnName = "id")
    private BolognaCycleEntity bolognaCycle;

    @ManyToOne
    @JoinColumn(name = "nqf_id", referencedColumnName = "id")
    private NationalQualificationsFrameworkEntity nationalQualificationFramework;

    @ManyToOne
    @JoinColumn(name = "eqf_id", referencedColumnName = "id")
    private EuropeanQualificationsFrameworkEntity europeanQualificationFramework;

    @ManyToOne
    @JoinColumn(name = "acc_bologna_cycle_id", referencedColumnName = "id")
    private BolognaCycleEntity accessedBolognaCycle;

    @ManyToOne
    @JoinColumn(name = "acc_nqf_id", referencedColumnName = "id")
    private NationalQualificationsFrameworkEntity accessedNationalQualificationFramework;

    @ManyToOne
    @JoinColumn(name = "acc_eqf_id", referencedColumnName = "id")
    private EuropeanQualificationsFrameworkEntity accessedEuropeanQualificationFramework;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'DURATION_UNIT'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "duration_unit", referencedColumnName = "code"))
    })
    private ReferenceDataEntity durationUnit;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trainingCourse")
    private TrainingCourseTrainingFormEntity trainingForm;


    @OneToMany(mappedBy = "trainingCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingCourseGraduationWayEntity> graduationWays;

    @ManyToOne
    @JoinColumn(name = "prof_group_id", referencedColumnName = "id")
    private ProfGroupEntity profGroup;

    @ManyToOne
    @JoinColumn(name = "graduation_document_type_id", referencedColumnName = "id")
    private GraduationDocumentTypeEntity graduationDocumentType;

    @OneToMany(mappedBy = "trainingCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingCourseSpecialityEntity> trainingCourseSpecialities;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trainingCourse")
    private TrainingCourseProgramExaminationEntity programExamination;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trainingCourse")
    private TrainingCourseDiplomaExaminationEntity diplomaExamination;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", insertable = false, updatable = false)
    @Where(clause = "doc_category = 'DEA'")
    private List<ApplicationAttachedDocEntity> diplomaExaminationAttachedDocs;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trainingCourse")
    private TrainingLocationExaminationEntity trainingLocationExamination;

    @OneToMany(mappedBy = "trainingCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingCourseUniversityExaminationEntity> trainingCourseUniversityExaminations;

    @OneToMany(mappedBy = "pk.trainingCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingCourseUniversityEntity> trainingCourseUniversities;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'RECOGNITION_CATEGORY'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "recognition_category_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity recognitionCategory;

    @Column(name = "manual_temp_uni_name")
    private String manualTempUniName;

}
