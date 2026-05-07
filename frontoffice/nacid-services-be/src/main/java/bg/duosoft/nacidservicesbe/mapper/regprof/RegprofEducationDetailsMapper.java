package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofTrainingExperienceEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 11:46
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        IntegerToBooleanMapper.class,
        RegprofExperienceMapper.class,
        RegprofEducationMapper.class
})
public abstract class RegprofEducationDetailsMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "appliesForCountry", source = "country")
    @Mapping(target = "notRestrictedFlag", source = "nonRevokedRightToPractice")
    @Mapping(target = "certificateProfQualification", source = "professionalQualificationRequested")
    @Mapping(target = "trainingCourse", source = "education")
    @Mapping(target = "experience", source = "experience")
    public abstract RegprofTrainingExperienceEntity toEntity(RegprofEducationDetailsDTO regprofEducationDetails);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "educationSelected", expression = "java(trainingExperienceEntity.getTrainingCourse() != null)")
    @Mapping(target = "experienceSelected", expression = "java(trainingExperienceEntity.getExperience() != null)")
    public abstract RegprofEducationDetailsDTO toDto(RegprofTrainingExperienceEntity trainingExperienceEntity);

    public List<RegprofTrainingExperienceEntity> toEntityList(RegprofEducationDetailsDTO regprofEducationDetails){
        List<RegprofTrainingExperienceEntity> entityList = new ArrayList<>();
        RegprofTrainingExperienceEntity entity = toEntity(regprofEducationDetails);
        entityList.add(entity);
        return entityList;
    }

    public RegprofEducationDetailsDTO toDtoFromList(List<RegprofTrainingExperienceEntity> trainingExperienceEntityList){
        if(trainingExperienceEntityList != null && trainingExperienceEntityList.size()>0){
            RegprofEducationDetailsDTO dto = toDto(trainingExperienceEntityList.get(0));
            return dto;
        }
        return null;
    }
}
