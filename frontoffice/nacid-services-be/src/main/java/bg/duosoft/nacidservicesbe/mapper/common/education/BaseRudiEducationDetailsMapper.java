package bg.duosoft.nacidservicesbe.mapper.common.education;

import bg.duosoft.nacidcoredata.mapper.nomenclature.DocumentReceiveMethodMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.education.RudiEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.mapper.common.address.ContactAddressMapper;
import bg.duosoft.nacidservicesbe.mapper.common.address.ReceiverAddressMapper;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.ServicesApplicantMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidservicesbe.mapper.common.person.NaturalPersonMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshared.web.mapper.YearStringToLocalDateMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 15:44
 */
@MapperConfig(componentModel = "spring",
        uses = {
                ReferenceDataMapper.class,
                UniversityDataMapper.class,
                EducationPlaceMapper.class,
                EducationFormMapper.class,
                GraduationWayMapper.class,
                ContactAddressMapper.class,
                ReceiverAddressMapper.class,
                ServicesApplicantMapper.class,
                NaturalPersonMapper.class,
                DocumentReceiveMethodMapper.class,
                AttachedDocumentMapper.class,
                IntegerToBooleanMapper.class,
                YearStringToLocalDateMapper.class
        }
)
public interface BaseRudiEducationDetailsMapper {

    @Mappings({

            @Mapping(target = "diplomaNum", source = "diploma.number"),
            @Mapping(target = "diplomaDate", source = "diploma.date"),
            @Mapping(target = "diplomaSeries", source = "diploma.series"),
            @Mapping(target = "diplomaRegNum", source = "diploma.registrationNumber"),
            @Mapping(target = "jointDegreeFlag", expression = "java(educationDetailsDTO != null && educationDetailsDTO.getUniversitiesData() != null && educationDetailsDTO.getUniversitiesData().size() > 1 ? 1: 0)"),
            @Mapping(target = "trainingStart", source = "startOfEducation"),
            @Mapping(target = "trainingEnd", source = "endOfEducation"),
            @Mapping(target = "trainingDuration", source = "educationDuration"),
            @Mapping(target = "durationUnit", source = "educationDurationType"),
            @Mapping(target = "credits", source = "credits"),
            @Mapping(target = "trainingUniversities", source = "universitiesData"),
            @Mapping(target = "trainingLocations", source = "educationPlaces"),
            @Mapping(target = "trainingForms", source = "educationFormWrapper"),
            @Mapping(target = "graduationWays", source = "graduationWayWrapper"),
            @Mapping(target = "originalEducationLevel", source = "originalGainedLevel"),
            @Mapping(target = "originalEducationLevelTranslated", source = "originalGainedLevelTranslated"),
    })
    void baseRudiEducationDetailsMapping(@MappingTarget RudiTrainingCourseEntity trainingCourse, RudiEducationDetailsDTO educationDetailsDTO);
}
