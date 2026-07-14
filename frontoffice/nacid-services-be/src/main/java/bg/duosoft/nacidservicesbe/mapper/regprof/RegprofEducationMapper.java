package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.services.common.education.SpecialityDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofTrainingCourseSpecialitiesEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 12:26
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class RegprofEducationMapper extends BaseObjectMapper<RegprofTrainingCourseEntity, RegprofEducationDTO> {

    @Autowired
    private ProfEducationMapper regprofProfEducationMapper;

    @Autowired
    private HigherEducationMapper regprofHigherEducationMapper;

    @Autowired
    private ADQEducationMapper regprofADQEducationMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "educationTypeCode", source = "kind.code")
    public abstract RegprofTrainingCourseEntity toEntity(RegprofEducationDTO regprofEducationDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "kind", expression = "java(bg.duosoft.nacidfrontofficedto.nomenclature.EducationType.fromCode(regprofTrainingCourseEntity.getEducationTypeCode()))")
    public abstract RegprofEducationDTO toDto(RegprofTrainingCourseEntity regprofTrainingCourseEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget RegprofTrainingCourseEntity entity, RegprofEducationDTO dto){
        if(dto.getKind().equals(EducationType.PROFESSIONAL_EDUCATION) || dto.getKind().equals(EducationType.SECONDARY_PROFESSIONAL_EDUCATION)){
            entity.setSecondaryTrainingCourse(regprofProfEducationMapper.toEntity(dto.getEducationEntrySecondary()));
        } else if(dto.getKind().equals(EducationType.HIGHER_EDUCATION)){
            entity.setHigherTrainingCourse(regprofHigherEducationMapper.toEntity(dto.getEducationEntryHigher()));
        } else if(dto.getKind().equals(EducationType.AFTER_DIPLOMA_QUALIFICATION)){
            entity.setPostgraduateTrainingCourse(regprofADQEducationMapper.toEntity(dto.getEducationEntryADQ()));
            entity.setHigherTrainingCourse(regprofHigherEducationMapper.toEntity(dto.getEducationEntryHigher()));
        }

        List<RegprofTrainingCourseSpecialitiesEntity> allSpecialities = new ArrayList<>();
        if(dto.getEducationEntrySecondary() != null &&
                (dto.getKind().equals(EducationType.PROFESSIONAL_EDUCATION) || dto.getKind().equals(EducationType.SECONDARY_PROFESSIONAL_EDUCATION))){
            dto.getEducationEntrySecondary().getSpecialities().stream().forEach(spec -> {
                RegprofTrainingCourseSpecialitiesEntity specEntity = new RegprofTrainingCourseSpecialitiesEntity();
                specEntity.setSecondarySpeciality(spec.getName());
                specEntity.setSecondarySpecialityId(spec.getId());
                allSpecialities.add(specEntity);
            });
        }

        if(dto.getEducationEntryHigher() != null && dto.getKind().equals(EducationType.HIGHER_EDUCATION) || dto.getKind().equals(EducationType.AFTER_DIPLOMA_QUALIFICATION)){
            dto.getEducationEntryHigher().getSpecialities().stream().forEach(spec -> {
                RegprofTrainingCourseSpecialitiesEntity specEntity = new RegprofTrainingCourseSpecialitiesEntity();
                specEntity.setHigherSpeciality(spec.getName());
                allSpecialities.add(specEntity);
            });
        }
        if(dto.getEducationEntryADQ() != null && dto.getKind().equals(EducationType.AFTER_DIPLOMA_QUALIFICATION)){
            dto.getEducationEntryADQ().getSpecialities().stream().forEach(spec -> {
                RegprofTrainingCourseSpecialitiesEntity specEntity = new RegprofTrainingCourseSpecialitiesEntity();
                specEntity.setSdkSpeciality(spec.getName());
                allSpecialities.add(specEntity);
            });
        }
        entity.setAllSpecialities(allSpecialities);
    }

    @AfterMapping
    public void afterToDto(@MappingTarget RegprofEducationDTO dto, RegprofTrainingCourseEntity entity) {
        if(dto.getKind().equals(EducationType.PROFESSIONAL_EDUCATION) || dto.getKind().equals(EducationType.SECONDARY_PROFESSIONAL_EDUCATION)){
            dto.setEducationEntrySecondary(regprofProfEducationMapper.toDto(entity.getSecondaryTrainingCourse()));
            dto.getEducationEntrySecondary().setSpecialities(new ArrayList<>());
        } else if(dto.getKind().equals(EducationType.HIGHER_EDUCATION)){
            dto.setEducationEntryHigher(regprofHigherEducationMapper.toDto(entity.getHigherTrainingCourse()));
            dto.getEducationEntryHigher().setSpecialities(new ArrayList<>());
        } else if(dto.getKind().equals(EducationType.AFTER_DIPLOMA_QUALIFICATION)){
            dto.setEducationEntryADQ(regprofADQEducationMapper.toDto(entity.getPostgraduateTrainingCourse()));
            dto.setEducationEntryHigher(regprofHigherEducationMapper.toDto(entity.getHigherTrainingCourse()));
            dto.getEducationEntryHigher().setSpecialities(new ArrayList<>());
            dto.getEducationEntryADQ().setSpecialities(new ArrayList<>());
        }

        entity.getAllSpecialities().stream().forEach(tcSpec -> {
            if(StringUtils.hasText(tcSpec.getHigherSpeciality())){
                dto.getEducationEntryHigher().getSpecialities().add(new SpecialityDTO(null, tcSpec.getHigherSpeciality(), null));
            } else if(StringUtils.hasText(tcSpec.getSecondarySpeciality())){
                dto.getEducationEntrySecondary().getSpecialities().add(new SpecialityDTO(tcSpec.getSecondarySpecialityId(), tcSpec.getSecondarySpeciality(), null));
            } else if(StringUtils.hasText(tcSpec.getSdkSpeciality())){
                dto.getEducationEntryADQ().getSpecialities().add(new SpecialityDTO(null, tcSpec.getSdkSpeciality(), null));
            }
        });
    }
}
