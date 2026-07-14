package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfExperienceDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfExperienceDocTypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:24
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ProfExperienceDocTypeMapper extends BaseNomenclatureMapper<ProfExperienceDocTypeEntity, ProfExperienceDocTypeDTO> {

    @Mapping(target = "isActive", source = "active")
    public abstract ProfExperienceDocTypeDTO toDto(ProfExperienceDocTypeEntity entity);
}
