package bg.duosoft.nacidkeycloakservices.mapper;

import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.model.entity.EUserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 9:56
 */
@Mapper(componentModel = "spring")
public abstract class BaseUserDetailsMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract BaseUserDetailsDTO toDto(EUserEntity entity);

    public abstract List<BaseUserDetailsDTO> toDtoListFromEntities(List<EUserEntity> entityList);
}
