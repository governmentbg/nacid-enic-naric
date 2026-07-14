package bg.duosoft.nacidservicesbe.mapper.herecognition;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.mapper.common.education.BaseRudiEducationDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.education.SpecialityMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 15:48
 */
@Mapper(componentModel = "spring", config = BaseRudiEducationDetailsMapper.class, uses = {
        SpecialityMapper.class,
        CountryMapper.class,
        RecognitionAimMapper.class,
})
public abstract class HeEducationDetailsMapper extends BaseObjectMapper<RudiTrainingCourseEntity, HeEducationDetailsDTO> {

    @InheritConfiguration(name = "baseRudiEducationDetailsMapping")
    @Mapping(target = "trainingSpecialities", source = "specialities")
    @Mapping(target = "prevDiplomaUniversity", source = "previousUniversityDiploma.universityName")
    @Mapping(target = "prevDiplomaUniversityId", source = "previousUniversityDiploma.universityNameId")
    @Mapping(target = "prevDiplomaEducationLevel", source = "previousUniversityDiploma.gainedLevel")
    @Mapping(target = "prevDiplomaGraduationDate", source = "previousUniversityDiploma.graduationYear")
    @Mapping(target = "prevDiplomaNotes", source = "previousUniversityDiploma.notes")
    @Mapping(target = "prevDiplomaSpeciality", source = "previousUniversityDiploma.speciality")
    @Mapping(target = "recognitionPurposes", source = "recognitionAimWrapper")
    @Mapping(target = "qualification", source = "gainedQualification")
    @Mapping(target = "originalQualification", source = "originalGainedQualification")
    public abstract RudiTrainingCourseEntity toEntity(HeEducationDetailsDTO educationDetailsDTO);


    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "highSchoolDiploma.country", source = "schoolCountry")
    @Mapping(target = "highSchoolDiploma.city", source = "schoolCity")
    @Mapping(target = "highSchoolDiploma.school", source = "schoolName")
    @Mapping(target = "highSchoolDiploma.notes", source = "schoolNotes")
    @Mapping(target = "highSchoolDiploma.graduationYear", source = "schoolGraduationDate")
    public abstract HeEducationDetailsDTO toDto(RudiTrainingCourseEntity trainingCourse);

    public List<RudiTrainingCourseEntity> toEntityListFromDto(HeEducationDetailsDTO educationDetails){
        RudiTrainingCourseEntity entity = toEntity(educationDetails);
        return Arrays.asList(entity);
    }

    public HeEducationDetailsDTO toDtoFromList(List<RudiTrainingCourseEntity> trainingCourses){
        if(trainingCourses != null && trainingCourses.size()>0){
            return toDto(trainingCourses.get(0));
        }
        return null;
    }
}
