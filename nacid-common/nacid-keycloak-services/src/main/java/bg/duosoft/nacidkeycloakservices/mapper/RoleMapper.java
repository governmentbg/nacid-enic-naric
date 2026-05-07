package bg.duosoft.nacidkeycloakservices.mapper;

import bg.duosoft.nacidfrontofficedto.user.access.RoleDTO;
import bg.duosoft.nacidkeycloakservices.model.entity.ERoleEntity;
import org.keycloak.representations.idm.RoleRepresentation;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 11:38
 */
@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract RoleDTO toDto(RoleRepresentation roleRepresentation);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract RoleDTO toDto(ERoleEntity entity);

    public abstract List<RoleDTO> toDtoListFromEntities(List<ERoleEntity> entityList);

    @InheritInverseConfiguration
    public abstract RoleRepresentation fromDto(RoleDTO role);
}
