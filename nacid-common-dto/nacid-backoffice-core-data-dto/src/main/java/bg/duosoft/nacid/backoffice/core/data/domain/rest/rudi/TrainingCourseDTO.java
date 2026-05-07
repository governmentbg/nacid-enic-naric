package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingCourseDTO {
    private Integer id;
    private String diplomaNumber;
    private LocalDate diplomaDate;
    private String diplomaSeries;
    private String diplomaRegistrationNumber;
    private List<TrainingLocationDTO> trainingLocations;
    private UniversityDTO baseUniversity;
    private PersonDTO diplomaOwner;
    private String diplomaOwnerEan;
    private CountryDTO schoolCountry;
    private SettlementDTO schoolSettlement;
    private String schoolCity;
    private String schoolName;
    private LocalDate schoolGraduationDate;
    private String schoolNotes;
    private UniversityDTO prevDiplomaUniversity;
    private ReferenceDataDTO prevDiplomaEduLevel;
    private LocalDate prevDiplomaGraduationDate;
    private String prevDiplomaSpeciality;
    private String prevDiplomaNotes;
    private LocalDate trainingStart;
    private LocalDate trainingEnd;
    private Double credits;
    private Integer ectsCredits;
    private Integer creditHours;
    private Double trainingDuration;
    private ReferenceDataDTO durationUnit;
    private TrainingCourseTrainingFormDTO trainingForm;
    private List<TrainingCourseGraduationWayDTO> graduationWays;
    private ProfGroupDTO profGroup;
    private GraduationDocumentTypeDTO graduationDocumentType;

    private String thesisTopic;
    private String thesisTopicEn;
    private LocalDate thesisDefenceDate;
    private Integer thesisBibliography;
    private Integer thesisVolume;
    private String thesisAnnotation;
    private String thesisAnnotationEn;
    private LanguageDTO thesisLanguage;

    private String scientificSupervisor;
    private String scientificSupervisorEn;
    private String reviewers;
    private String reviewersEn;
    private String juryChair;
    private String juryChairEn;
    private String juryMembers;
    private String juryMembersEn;

    private String qualification;
    private String originalQualification;
    private List<TrainingCourseSpecialityDTO> trainingCourseSpecialities;

    private TrainingCourseDiplomaExaminationDTO diplomaExamination;
    private TrainingCourseProgramExaminationDTO programExamination;
    private TrainingLocationExaminationDTO trainingLocationExamination;
    private List<TrainingCourseUniversityExaminationDTO> trainingCourseUniversityExaminations;

    private List<TrainingCourseUniversityDTO> trainingCourseUniversities;


    private String originalEduLevelName;
    private String originalEduLevelTranslated;
    private BolognaCycleDTO bolognaCycle;
    private NationalQualificationFrameworkDTO nationalQualificationFramework;
    private EuropeanQualificationFrameworkDTO europeanQualificationFramework;
    private BolognaCycleDTO accessedBolognaCycle;
    private NationalQualificationFrameworkDTO accessedNationalQualificationFramework;
    private EuropeanQualificationFrameworkDTO accessedEuropeanQualificationFramework;

    private ReferenceDataDTO recognitionCategory;
    private String manualTempUniName;
}
