package bg.duosoft.nacidservicesbe.mapper.unichecks;

import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
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
 * Date: 16.01.2023
 * Time: 15:41
 */
@Mapper(componentModel = "spring", config = BaseRudiEducationDetailsMapper.class, uses = {
        SpecialityMapper.class,
})
public abstract class UniChecksEducationDetailsMapper extends BaseObjectMapper<RudiTrainingCourseEntity, UniChecksEducationDetailsDTO> {

    @InheritConfiguration(name = "baseRudiEducationDetailsMapping")
    @Mapping(target = "trainingSpecialities", source = "specialities")
    @Mapping(target = "qualification", source = "gainedQualification")
    @Mapping(target = "originalQualification", source = "originalGainedQualification")
    @Mapping(target = "diplomaOwner", source = "diplomaHolder")
    @Mapping(target = "diplomaOwnerEan", source = "diplomaHolderEan")
    @Mapping(target = "recognitionCategory", source = "recognitionCategory")
    public abstract RudiTrainingCourseEntity toEntity(UniChecksEducationDetailsDTO educationDetailsDTO);


    @InheritInverseConfiguration(name = "toEntity")
    public abstract UniChecksEducationDetailsDTO toDto(RudiTrainingCourseEntity trainingCourse);

    public List<RudiTrainingCourseEntity> toEntityListFromDto(UniChecksEducationDetailsDTO educationDetails){
        RudiTrainingCourseEntity entity = toEntity(educationDetails);
        return Arrays.asList(entity);
    }

    public UniChecksEducationDetailsDTO toDtoFromList(List<RudiTrainingCourseEntity> trainingCourses){
        if(trainingCourses != null && trainingCourses.size()>0){
            return toDto(trainingCourses.get(0));
        }
        return null;
    }
}
