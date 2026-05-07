package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationNoteDTO;
import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationNoteEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.08.2023
 * Time: 15:07
 */
@Mapper(componentModel = "spring")
public abstract class ApplicationNoteMapper extends BaseObjectMapper<ApplicationNoteEntity, ApplicationNoteDTO> {

    @Autowired
    private KeycloakUserService keycloakUserService;

    private static final String APP_NOTES_USER_GROUP = "BO_users";

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract ApplicationNoteEntity toEntity(ApplicationNoteDTO applicationNoteDto);

    @InheritInverseConfiguration
    public abstract ApplicationNoteDTO toDto(ApplicationNoteEntity applicationNoteEntity);

    @AfterMapping
    public void afterToDto(ApplicationNoteEntity source, @MappingTarget ApplicationNoteDTO target) {
        target.setUserCreatedFullName(selectUserFullName(target.getUserCreated()));
        target.setUserUpdatedFullName(selectUserFullName(target.getUserUpdated()));
    }

    private String selectUserFullName(String userUpdated) {
        if (StringUtils.hasText(userUpdated)) {
            Map<String, NacidUserDetailsDTO> usersMap = keycloakUserService.getUsersMapFromGroupHierarchyCached(APP_NOTES_USER_GROUP);
            if (!CollectionUtils.isEmpty(usersMap)) {
                BaseUserDetailsDTO userDetails = usersMap.get(userUpdated);
                if (Objects.nonNull(userDetails)) {
                    return userDetails.getFullName();
                }
            }
        }

        return null;
    }
}
