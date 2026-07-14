package bg.duosoft.nacidservicesbe.mapper.common.education;

import bg.duosoft.nacidfrontofficedto.services.common.education.UniversityDataDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseUniversityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.10.2022
 * Time: 13:07
 */
@Mapper(componentModel = "spring")
public abstract class UniversityDataMapper extends BaseObjectMapper<RudiTrainingCourseUniversityEntity, UniversityDataDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uniName", source = "name")
    @Mapping(target = "uniId", source = "nameId")
    @Mapping(target = "facultyId", source = "facultyId")
    @Mapping(target = "facultyName", source = "faculty")
    @Mapping(target = "universityContact", source = "universityContact")
    public abstract RudiTrainingCourseUniversityEntity toEntity(UniversityDataDTO universityDataDTO);

    @InheritInverseConfiguration
    public abstract UniversityDataDTO toDto(RudiTrainingCourseUniversityEntity rudiTrainingCourseUniversityEntity);
}
