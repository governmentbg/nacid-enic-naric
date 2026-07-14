package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfGroupEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfGroupDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 14:55
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReferenceDataMapper.class})
public abstract class ProfGroupMapper extends BaseNomenclatureMapper<ProfGroupEntity, ProfGroupDTO>{

    @Mapping(target = "isActive", source = "active")
    public abstract ProfGroupDTO toDto(ProfGroupEntity entity);
}
