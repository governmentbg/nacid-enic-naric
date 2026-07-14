package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeConfigEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.nomenclature.GraduationDocTypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:23
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class GraduationDocTypeMapper extends BaseNomenclatureMapper<GraduationDocTypeEntity, GraduationDocTypeDTO>{

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "educationTypes", source = "configs")
    public abstract GraduationDocTypeDTO toDto(GraduationDocTypeEntity entity);

    public EducationType toEducationTypeFromConfig(GraduationDocTypeConfigEntity configEntity){
        EducationType educationType = EducationType.fromCode(configEntity.getId().getEducationType());
        return educationType;
    }

    public abstract List<EducationType> toEducationTypesFromConfigs(List<GraduationDocTypeConfigEntity> configEntityList);

}
