package bg.duosoft.nacidkeycloakservices.mapper;

import bg.duosoft.nacidfrontofficedto.user.access.filter.GroupFilterDTO;
import bg.duosoft.nacidkeycloakservices.model.filter.GroupFilter;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 17:24
 */
@Mapper(componentModel = "spring")
public abstract class GroupFilterMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "sortOrder", source = "order")
    @Mapping(target = "sortColumn", source = "orderBy")
    public abstract GroupFilter toFilter(GroupFilterDTO dto);
}
