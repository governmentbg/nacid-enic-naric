package bg.duosoft.nacidservicesbe.mapper.docdegrees;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.LanguageMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ProfGroupMapper;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.mapper.common.education.BaseRudiEducationDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.education.SpecialityMapper;
import bg.duosoft.nacidservicesbe.mapper.herecognition.RecognitionAimMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.01.2023
 * Time: 17:33
 */
@Mapper(componentModel = "spring", config = BaseRudiEducationDetailsMapper.class, uses = {
        SpecialityMapper.class,
        CountryMapper.class,
        RecognitionAimMapper.class,
        ProfGroupMapper.class,
        LanguageMapper.class,
})
public abstract class DocEducationDetailsMapper extends BaseObjectMapper<RudiTrainingCourseEntity, DocEducationDetailsDTO> {

    @InheritConfiguration(name = "baseRudiEducationDetailsMapping")
    @Mapping(target = "profGroup", source = "gainedLevelProfGroup")
    @Mapping(target = "prevDiplomaUniversity", source = "previousUniversityDiploma.universityName")
    @Mapping(target = "prevDiplomaUniversityId", source = "previousUniversityDiploma.universityNameId")
    @Mapping(target = "prevDiplomaEducationLevel", source = "previousUniversityDiploma.gainedLevel")
    @Mapping(target = "prevDiplomaGraduationDate", source = "previousUniversityDiploma.graduationYear")
    @Mapping(target = "prevDiplomaNotes", source = "previousUniversityDiploma.notes")
    @Mapping(target = "prevDiplomaSpeciality", source = "previousUniversityDiploma.speciality")
    @Mapping(target = "thesisTopic", source = "dissertationTheme")
    @Mapping(target = "thesisTopicEn", source = "dissertationThemeEn")
    @Mapping(target = "thesisDefenceDate", source = "dissertationDate")
    @Mapping(target = "thesisLanguage", source = "dissertationLanguage")
    @Mapping(target = "thesisBibliographyCount", source = "dissertationBiblioTitlesCount")
    @Mapping(target = "thesisVolumeCount", source = "dissertationPagesCount")
    @Mapping(target = "thesisAnnotation", source = "dissertationAnnotation")
    @Mapping(target = "thesisAnnotationEn", source = "dissertationAnnotationEn")
    @Mapping(target = "recognitionCategory", source = "recognitionCategory")
    public abstract RudiTrainingCourseEntity toEntity(DocEducationDetailsDTO educationDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract DocEducationDetailsDTO toDto(RudiTrainingCourseEntity trainingCourse);

    public List<RudiTrainingCourseEntity> toEntityListFromDto(DocEducationDetailsDTO educationDetails){
        RudiTrainingCourseEntity entity = toEntity(educationDetails);
        return Arrays.asList(entity);
    }

    public DocEducationDetailsDTO toDtoFromList(List<RudiTrainingCourseEntity> trainingCourses){
        if(trainingCourses != null && trainingCourses.size()>0){
            return toDto(trainingCourses.get(0));
        }
        return null;
    }

}
