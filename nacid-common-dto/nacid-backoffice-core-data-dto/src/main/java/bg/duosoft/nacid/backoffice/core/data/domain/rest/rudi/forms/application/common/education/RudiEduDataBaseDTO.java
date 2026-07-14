package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.UniversityAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BolognaCycleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.EuropeanQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.NationalQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingLocationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.mandatory.RudiMandatoryEduData;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class RudiEduDataBaseDTO implements RudiMandatoryEduData {
    private Integer applicationId;
    private Integer efilingId;
    private Integer trainingCourseId;

    private Integer baseUniversityId;
    private Integer diplomaTypeId;

    private String diplomaNumber;
    private LocalDate diplomaDate;
    private String diplomaSeries;
    private String diplomaRegistrationNumber;
    private String diplomaOwnerFirstName;
    private String diplomaOwnerMiddleName;
    private String diplomaOwnerLastName;
    private String diplomaOwnerCivilId;
    private LocalDate diplomaOwnerBirthDate;
    private String diplomaOwnerBirthCountry;
    private String diplomaOwnerEan;

    private List<TrainingLocationDTO> trainingLocations;

    private String trainingStart;
    private String trainingEnd;
    private Double trainingDuration;
    private ReferenceDataDTO durationUnit;

    private ReferenceDataDTO trainingForm;
    private String trainingFormNotes;

    private Boolean graduationWayOther;
    private String graduationWayNotes;

    private Integer profGroupId;
    private Integer graduationDocumentTypeId;

    private Double credits;
    private Integer ectsCredits;
    private Integer creditHours;

    private UniversityAutocompleteDTO prevDiplomaUniversity;
    private ReferenceDataDTO prevDiplomaEduLevel;
    private String prevDiplomaGraduationDate;
    private String prevDiplomaSpeciality;
    private String prevDiplomaNotes;

    private TrainingCourseUniversityDTO primaryUniversity;
    private List<TrainingCourseUniversityDTO> secondaryUniversities;
    private Boolean isJointDegree;
    private String originalEduLevelName;
    private String originalEduLevelTranslated;
    private BolognaCycleDTO bolognaCycle;
    private NationalQualificationFrameworkDTO nationalQualificationFramework;
    private EuropeanQualificationFrameworkDTO europeanQualificationFramework;
    private BolognaCycleDTO accessedBolognaCycle;
    private NationalQualificationFrameworkDTO accessedNationalQualificationFramework;
    private EuropeanQualificationFrameworkDTO accessedEuropeanQualificationFramework;
    private ReferenceDataDTO recognitionCategory;
    private String qualification;
    private String originalQualification;
    private String manualTempUniName;

}
