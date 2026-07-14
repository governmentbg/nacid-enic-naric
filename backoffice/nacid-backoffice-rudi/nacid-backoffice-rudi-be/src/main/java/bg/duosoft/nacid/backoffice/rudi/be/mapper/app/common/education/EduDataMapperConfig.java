package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.RudiEduDataBaseDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshared.web.mapper.YearStringToLocalDateMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@MapperConfig(componentModel = "spring",
        uses = {
                IntegerToBooleanMapper.class,
                YearStringToLocalDateMapper.class,
        }
)
public interface EduDataMapperConfig {

    @Mappings({
            @Mapping(target = "applicationId", source = "application.id"),
            @Mapping(target = "efilingId", source = "application.efilingId"),
            @Mapping(target = "trainingCourseId", source = "trainingCourse.id"),
            @Mapping(target = "baseUniversityId", source = "trainingCourse.baseUniversity.id"),
            @Mapping(target = "diplomaNumber", source = "trainingCourse.diplomaNumber"),
            @Mapping(target = "diplomaDate", source = "trainingCourse.diplomaDate"),
            @Mapping(target = "diplomaSeries", source = "trainingCourse.diplomaSeries"),
            @Mapping(target = "diplomaRegistrationNumber", source = "trainingCourse.diplomaRegistrationNumber"),
            @Mapping(target = "diplomaOwnerEan", source = "trainingCourse.diplomaOwnerEan"),
            @Mapping(target = "trainingLocations", source = "trainingCourse.trainingLocations"),
            @Mapping(target = "trainingStart", source = "trainingCourse.trainingStart"),
            @Mapping(target = "trainingEnd", source = "trainingCourse.trainingEnd"),
            @Mapping(target = "trainingDuration", source = "trainingCourse.trainingDuration"),
            @Mapping(target = "durationUnit", source = "trainingCourse.durationUnit"),
            @Mapping(target = "trainingForm", source = "trainingCourse.trainingForm.trainingForm"),
            @Mapping(target = "trainingFormNotes", source = "trainingCourse.trainingForm.notes"),
            @Mapping(target = "credits", source = "trainingCourse.credits"),
            @Mapping(target = "creditHours", source = "trainingCourse.creditHours"),
            @Mapping(target = "ectsCredits", source = "trainingCourse.ectsCredits"),
            @Mapping(target = "prevDiplomaEduLevel", source = "trainingCourse.prevDiplomaEduLevel"),
            @Mapping(target = "prevDiplomaGraduationDate", source = "trainingCourse.prevDiplomaGraduationDate"),
            @Mapping(target = "prevDiplomaSpeciality", source = "trainingCourse.prevDiplomaSpeciality"),
            @Mapping(target = "prevDiplomaNotes", source = "trainingCourse.prevDiplomaNotes"),
            @Mapping(target = "profGroupId", source = "trainingCourse.profGroup.id"),
            @Mapping(target = "graduationDocumentTypeId", source = "trainingCourse.graduationDocumentType.id"),
            @Mapping(target = "originalEduLevelName", source = "trainingCourse.originalEduLevelName"),
            @Mapping(target = "originalEduLevelTranslated", source = "trainingCourse.originalEduLevelTranslated"),

            @Mapping(target = "bolognaCycle", source = "trainingCourse.bolognaCycle"),
            @Mapping(target = "nationalQualificationFramework", source = "trainingCourse.nationalQualificationFramework"),
            @Mapping(target = "europeanQualificationFramework", source = "trainingCourse.europeanQualificationFramework"),
            @Mapping(target = "accessedBolognaCycle", source = "trainingCourse.accessedBolognaCycle"),
            @Mapping(target = "accessedNationalQualificationFramework", source = "trainingCourse.accessedNationalQualificationFramework"),
            @Mapping(target = "accessedEuropeanQualificationFramework", source = "trainingCourse.accessedEuropeanQualificationFramework"),
            @Mapping(target = "recognitionCategory", source = "trainingCourse.recognitionCategory"),
            @Mapping(target = "qualification", source = "trainingCourse.qualification"),
            @Mapping(target = "originalQualification", source = "trainingCourse.originalQualification"),
            @Mapping(target = "manualTempUniName", source = "trainingCourse.manualTempUniName")
    })
    void baseEducationDataSectionMapping(@MappingTarget RudiEduDataBaseDTO target, RudiApplicationDTO source);

}
