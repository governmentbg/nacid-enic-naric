package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfGroupEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.PersonEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 13:17
 */
@Entity
@Table(name = "rudi_training_course", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiTrainingCourseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apn_id", unique = true)
    private Integer rudiApplicationId;

    @Column(name = "diploma_num")
    private String diplomaNum;

    @Column(name = "diploma_date")
    private LocalDate diplomaDate;

    @Column(name = "diploma_series")
    private String diplomaSeries;

    @Column(name = "diploma_registration_number")
    private String diplomaRegNum;

    @Column(name = "joint_degree_flag")
    private Integer jointDegreeFlag;

    @Column(name = "training_start")
    private LocalDate trainingStart;

    @Column(name = "training_end")
    private LocalDate trainingEnd;

    @Column(name = "training_duration")
    private Double trainingDuration;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'DURATION_UNIT'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "duration_unit", referencedColumnName="code"))
    })
    private ReferenceDataEntity durationUnit;

    @Column(name = "credits")
    private Float credits;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'RECOGNITION_CATEGORY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "recognition_category_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity recognitionCategory;

    @Column(name = "original_edu_level")
    private String originalEducationLevel;

    @Column(name = "original_edu_level_translated")
    private String originalEducationLevelTranslated;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "original_qualification")
    private String originalQualification;

    @ManyToOne
    @JoinColumn(name = "school_country", referencedColumnName = "code")
    private CountryEntity schoolCountry;

    @Column(name = "school_city")
    private String schoolCity;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_graduation_date")
    private LocalDate schoolGraduationDate;

    @Column(name = "school_notes")
    private String schoolNotes;

    @Column(name = "prev_diploma_university_id")
    private Integer prevDiplomaUniversityId;

    @Column(name = "prev_diploma_university")
    private String prevDiplomaUniversity;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'EDUCATION_LEVEL'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "prev_diploma_edu_level", referencedColumnName="code"))
    })
    private ReferenceDataEntity prevDiplomaEducationLevel;

    @Column(name = "prev_diploma_graduation_date")
    private LocalDate prevDiplomaGraduationDate;

    @Column(name = "prev_diploma_notes")
    private String prevDiplomaNotes;

    @Column(name = "prev_diploma_speciality")
    private String prevDiplomaSpeciality;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private PersonEntity diplomaOwner;

    @Column(name = "owner_ean")
    private String diplomaOwnerEan;

    @ManyToOne
    @JoinColumn(name = "prof_group_id", referencedColumnName = "id")
    private ProfGroupEntity profGroup;

    @Column(name = "thesis_topic")
    private String thesisTopic;

    @Column(name = "thesis_topic_en")
    private String thesisTopicEn;

    @Column(name = "thesis_defence_date")
    private LocalDate thesisDefenceDate;

    @Column(name = "thesis_bibliography")
    private Integer thesisBibliographyCount;

    @Column(name = "thesis_volume")
    private Integer thesisVolumeCount;

    @Column(name = "thesis_annotation")
    private String thesisAnnotation;

    @Column(name = "thesis_annotation_en")
    private String thesisAnnotationEn;

    @ManyToOne
    @JoinColumn(name = "thesis_language_code", referencedColumnName = "code")
    private LanguageEntity thesisLanguage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RudiTrainingCourseGraduationWayEntity> graduationWays;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RudiTrainingCourseTrainingFormEntity> trainingForms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RudiTrainingCourseUniversityEntity> trainingUniversities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RudiTrainingLocationEntity> trainingLocations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RudiTrainingCourseSpecialityEntity> trainingSpecialities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingCourse", orphanRemoval = true)
    private List<RudiRecognitionPurposeEntity> recognitionPurposes;
}
