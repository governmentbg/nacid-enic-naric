package bg.duosoft.nacidkeycloakservices.mapper;

import bg.duosoft.nacidfrontofficedto.user.access.GroupDTO;
import bg.duosoft.nacidkeycloakservices.model.entity.EGroupAttribute;
import bg.duosoft.nacidkeycloakservices.model.entity.EGroupEntity;
import bg.duosoft.nacidkeycloakservices.util.attribute.AttributesUtil;
import bg.duosoft.nacidkeycloakservices.util.attribute.Extractor;
import bg.duosoft.nacidkeycloakservices.util.attribute.GroupAttributes;
import org.keycloak.representations.idm.GroupRepresentation;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 12:52
 */
@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract GroupDTO toDto(EGroupEntity entity);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract GroupDTO toDto(GroupRepresentation groupRepresentation);

    @InheritInverseConfiguration
    public abstract GroupRepresentation fromDto(GroupDTO group);

    @AfterMapping
    public void afterMappingFromDto(GroupDTO source, @MappingTarget GroupRepresentation target) {
        Map<String, List<String>> attributes = target.getAttributes();
        if (Objects.isNull(attributes)) {
            attributes = new HashMap<>();
        }
        if (!StringUtils.hasText(target.getId())) {
            target.setId(null);
        }
        AttributesUtil.setAttribute(attributes, GroupAttributes.DESCRIPTION, source.getDescription());
        target.setAttributes(attributes);
    }

    @AfterMapping
    public void afterMappingToDto(GroupRepresentation source, @MappingTarget GroupDTO target) {
        Map<String, List<String>> attributes = source.getAttributes();
        if (Objects.nonNull(attributes)) {
            target.setDescription(Extractor.firstOrNull(attributes.get(GroupAttributes.DESCRIPTION)));
        }
    }

    @AfterMapping
    public void afterMappingToDto(EGroupEntity source, @MappingTarget GroupDTO target) {
        List<EGroupAttribute> attributes = source.getGroupAttributes();
        if (Objects.nonNull(attributes)) {
            EGroupAttribute descriptionAttribute = attributes.stream().filter(attr -> attr.getName().equals(GroupAttributes.DESCRIPTION)).findFirst().orElse(null);
            if (descriptionAttribute != null) {
                target.setDescription(descriptionAttribute.getValue());
            }
        }
    }

    public abstract List<GroupDTO> toDtoListFromRepresentations(List<GroupRepresentation> groupRepresentationList);

    public abstract List<GroupDTO> toDtoListFromEntities(List<EGroupEntity> entityList);
}
