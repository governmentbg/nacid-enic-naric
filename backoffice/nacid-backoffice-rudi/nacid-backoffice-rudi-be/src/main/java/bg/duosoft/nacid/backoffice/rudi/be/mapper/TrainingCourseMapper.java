package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.BolognaCycleEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EuropeanQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NationalQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDiplomaExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.PersonMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.*;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.*;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
        TrainingLocationMapper.class,
        PersonMapper.class,
        UniversityMapper.class,
        ReferenceDataMapper.class,
        SettlementMapper.class,
        TrainingCourseTrainingFormMapper.class,
        ProfGroupMapper.class,
        LanguageMapper.class,
        GraduationDocumentTypeMapper.class,
        TrainingCourseSpecialityMapper.class,
        TrainingCourseUniversityExaminationMapper.class,
        TrainingCourseProgramExaminationMapper.class,
        TrainingLocationExaminationMapper.class,
        TrainingCourseUniversityMapper.class,
        TrainingCourseDiplomaExaminationMapper.class,
        BolognaCycleMapper.class,
        NationalQualificationsFrameworkMapper.class,
        EuropeanQualificationsFrameworkMapper.class,
})
public abstract class TrainingCourseMapper extends BaseObjectMapper<TrainingCourseEntity, TrainingCourseDTO> {

    @Autowired
    private ApplicationAttachedDocMapper applicationAttachedDocMapper;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "diplomaNumber", source = "diplomaNumber")
    @Mapping(target = "diplomaDate", source = "diplomaDate")
    @Mapping(target = "diplomaSeries", source = "diplomaSeries")
    @Mapping(target = "diplomaRegistrationNumber", source = "diplomaRegistrationNumber")
    @Mapping(target = "trainingLocations", source = "trainingLocations")
    @Mapping(target = "baseUniversity", source = "baseUniversity")
    @Mapping(target = "diplomaOwner", source = "diplomaOwner")
    @Mapping(target = "diplomaOwnerEan", source = "diplomaOwnerEan")
    @Mapping(target = "schoolCountry", source = "schoolCountry")
    @Mapping(target = "schoolSettlement", source = "schoolSettlement")
    @Mapping(target = "schoolCity", source = "schoolCity")
    @Mapping(target = "schoolName", source = "schoolName")
    @Mapping(target = "schoolGraduationDate", source = "schoolGraduationDate")
    @Mapping(target = "schoolNotes", source = "schoolNotes")
    @Mapping(target = "prevDiplomaUniversity", source = "prevDiplomaUniversity")
    @Mapping(target = "prevDiplomaEduLevel", source = "prevDiplomaEduLevel")
    @Mapping(target = "prevDiplomaGraduationDate", source = "prevDiplomaGraduationDate")
    @Mapping(target = "prevDiplomaNotes", source = "prevDiplomaNotes")
    @Mapping(target = "prevDiplomaSpeciality", source = "prevDiplomaSpeciality")
    @Mapping(target = "trainingStart", source = "trainingStart")
    @Mapping(target = "trainingEnd", source = "trainingEnd")
    @Mapping(target = "credits", source = "credits")
    @Mapping(target = "creditHours", source = "creditHours")
    @Mapping(target = "ectsCredits", source = "ectsCredits")
    @Mapping(target = "trainingDuration", source = "trainingDuration")
    @Mapping(target = "durationUnit", source = "durationUnit")
    @Mapping(target = "trainingForm", source = "trainingForm")
    @Mapping(target = "profGroup", source = "profGroup")
    @Mapping(target = "graduationDocumentType", source = "graduationDocumentType")
    @Mapping(target = "thesisTopic", source = "thesisTopic")
    @Mapping(target = "thesisTopicEn", source = "thesisTopicEn")
    @Mapping(target = "thesisDefenceDate", source = "thesisDefenceDate")
    @Mapping(target = "thesisBibliography", source = "thesisBibliography")
    @Mapping(target = "thesisVolume", source = "thesisVolume")
    @Mapping(target = "thesisAnnotation", source = "thesisAnnotation")
    @Mapping(target = "thesisAnnotationEn", source = "thesisAnnotationEn")
    @Mapping(target = "scientificSupervisor", source = "scientificSupervisor")
    @Mapping(target = "scientificSupervisorEn", source = "scientificSupervisorEn")
    @Mapping(target = "reviewers", source = "reviewers")
    @Mapping(target = "reviewersEn", source = "reviewersEn")
    @Mapping(target = "juryChair", source = "juryChair")
    @Mapping(target = "juryChairEn", source = "juryChairEn")
    @Mapping(target = "juryMembers", source = "juryMembers")
    @Mapping(target = "juryMembersEn", source = "juryMembersEn")
    @Mapping(target = "thesisLanguage", source = "thesisLanguage")
    @Mapping(target = "qualification", source = "qualification")
    @Mapping(target = "originalQualification", source = "originalQualification")
    @Mapping(target = "trainingCourseSpecialities", source = "trainingCourseSpecialities")
    @Mapping(target = "trainingCourseUniversityExaminations", source = "trainingCourseUniversityExaminations")
    @Mapping(target = "programExamination", source = "programExamination")
    @Mapping(target = "diplomaExamination", source = "diplomaExamination")
    @Mapping(target = "trainingLocationExamination", source = "trainingLocationExamination")
    @Mapping(target = "trainingCourseUniversities", source = "trainingCourseUniversities")
    @Mapping(target = "recognitionCategory", source = "recognitionCategory")
    @Mapping(target = "manualTempUniName", source = "manualTempUniName")
    public abstract TrainingCourseDTO toDto(TrainingCourseEntity entity);

    @InheritInverseConfiguration
    public abstract TrainingCourseEntity toEntity(TrainingCourseDTO dto);

    @AfterMapping
    protected void afterToEntity(TrainingCourseDTO source, @MappingTarget TrainingCourseEntity target) {
        setMissingFields(target);
        setDiplomaExaminationAttachedDocs(source.getDiplomaExamination(), target);
    }

    @BeforeMapping
    protected void beforeToEntity(TrainingCourseDTO source, @MappingTarget TrainingCourseEntity target) {
        this.overrideDtoData(source);
    }

    public void overrideDtoData(TrainingCourseDTO dto) {
        if (Objects.nonNull(dto)) {
            ReferenceDataUtils.setDefaultDomain(dto.getDurationUnit(), ReferenceDataDomain.DURATION_UNIT);
        }
    }

    private void setMissingFields(TrainingCourseEntity target) {
        TrainingCourseTrainingFormEntity trainingForm = target.getTrainingForm();
        if (Objects.nonNull(trainingForm)) {
            trainingForm.setId(target.getId());
            if (Objects.isNull(trainingForm.getTrainingCourse())) {
                trainingForm.setTrainingCourse(target);
            }
        }
        List<TrainingLocationEntity> trainingLocations = target.getTrainingLocations();
        if (!CollectionUtils.isEmpty(trainingLocations)) {
            for (TrainingLocationEntity trainingLocation : trainingLocations) {
                trainingLocation.setTrainingCourse(target);
            }
        }
        List<TrainingCourseGraduationWayEntity> graduationWays = target.getGraduationWays();
        if (!CollectionUtils.isEmpty(graduationWays)) {
            for (TrainingCourseGraduationWayEntity graduationWay : graduationWays) {
                graduationWay.setTrainingCourse(target);
            }
        }
        List<TrainingCourseSpecialityEntity> trainingCourseSpecialities = target.getTrainingCourseSpecialities();
        if (!CollectionUtils.isEmpty(trainingCourseSpecialities)) {
            for (TrainingCourseSpecialityEntity trainingCourseSpeciality : trainingCourseSpecialities) {
                trainingCourseSpeciality.setTrainingCourse(target);
            }
        }
        List<TrainingCourseUniversityExaminationEntity> trainingCourseUniversityExaminations = target.getTrainingCourseUniversityExaminations();
        if (!CollectionUtils.isEmpty(trainingCourseUniversityExaminations)) {
            for (TrainingCourseUniversityExaminationEntity trainingCourseUniversityExamination : trainingCourseUniversityExaminations) {
                trainingCourseUniversityExamination.setTrainingCourse(target);
            }
        }

        List<TrainingCourseUniversityEntity> trainingCourseUniversities = target.getTrainingCourseUniversities();
        if (!CollectionUtils.isEmpty(trainingCourseUniversities)) {
            for (TrainingCourseUniversityEntity trainingCourseUniversity : trainingCourseUniversities) {
                trainingCourseUniversity.getPk().setTrainingCourse(target);
            }
        }

        TrainingCourseProgramExaminationEntity programExamination = target.getProgramExamination();
        if (Objects.nonNull(programExamination)) {
            programExamination.setId(target.getId());
            if (Objects.isNull(programExamination.getTrainingCourse())) {
                programExamination.setTrainingCourse(target);
            }
        }

        TrainingLocationExaminationEntity locationExamination = target.getTrainingLocationExamination();
        if (Objects.nonNull(locationExamination)) {
            locationExamination.setId(target.getId());
            if (Objects.isNull(locationExamination.getTrainingCourse())) {
                locationExamination.setTrainingCourse(target);
            }
        }

        TrainingCourseDiplomaExaminationEntity diplomaExamination = target.getDiplomaExamination();
        if (Objects.nonNull(diplomaExamination)) {
            if (Objects.isNull(diplomaExamination.getTrainingCourse())) {
                diplomaExamination.setTrainingCourse(target);
            }
        }



        if (Objects.nonNull(target.getBolognaCycle())) {
            Integer id = target.getBolognaCycle().getId();
            if (Objects.nonNull(id)) {
                target.setBolognaCycle(new BolognaCycleEntity());
                target.getBolognaCycle().setId(id);
            } else {
                target.setBolognaCycle(null);
            }
        }

        if (Objects.nonNull(target.getNationalQualificationFramework())) {
            Integer id = target.getNationalQualificationFramework().getId();
            if (Objects.nonNull(id)) {
                target.setNationalQualificationFramework(new NationalQualificationsFrameworkEntity());
                target.getNationalQualificationFramework().setId(id);
            } else {
                target.setNationalQualificationFramework(null);
            }
        }

        if (Objects.nonNull(target.getEuropeanQualificationFramework())) {
            Integer id = target.getEuropeanQualificationFramework().getId();
            if (Objects.nonNull(id)) {
                target.setEuropeanQualificationFramework(new EuropeanQualificationsFrameworkEntity());
                target.getEuropeanQualificationFramework().setId(id);
            } else {
                target.setEuropeanQualificationFramework(null);
            }
        }

        if (Objects.nonNull(target.getAccessedBolognaCycle())) {
            Integer id = target.getAccessedBolognaCycle().getId();
            if (Objects.nonNull(id)) {
                target.setAccessedBolognaCycle(new BolognaCycleEntity());
                target.getAccessedBolognaCycle().setId(id);
            } else {
                target.setAccessedBolognaCycle(null);
            }
        }

        if (Objects.nonNull(target.getAccessedNationalQualificationFramework())) {
            Integer id = target.getAccessedNationalQualificationFramework().getId();
            if (Objects.nonNull(id)) {
                target.setAccessedNationalQualificationFramework(new NationalQualificationsFrameworkEntity());
                target.getAccessedNationalQualificationFramework().setId(id);
            } else {
                target.setAccessedNationalQualificationFramework(null);
            }
        }

        if (Objects.nonNull(target.getAccessedEuropeanQualificationFramework())) {
            Integer id = target.getAccessedEuropeanQualificationFramework().getId();
            if (Objects.nonNull(id)) {
                target.setAccessedEuropeanQualificationFramework(new EuropeanQualificationsFrameworkEntity());
                target.getAccessedEuropeanQualificationFramework().setId(id);
            } else {
                target.setAccessedEuropeanQualificationFramework(null);
            }
        }
    }

    private void setDiplomaExaminationAttachedDocs(TrainingCourseDiplomaExaminationDTO diplomaExam, TrainingCourseEntity target) {
        if (Objects.nonNull(diplomaExam)) {
            List<AttachedDocDTO> attachedDocs = diplomaExam.getAttachedDocs();
            if (CollectionUtils.isEmpty(attachedDocs)) {
                target.setDiplomaExaminationAttachedDocs(new ArrayList<>());
                return;
            }
            target.setDiplomaExaminationAttachedDocs(applicationAttachedDocMapper.toEntityList(attachedDocs));
        }
    }

}
