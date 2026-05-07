package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationResponsibleUsersService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.NacidUserAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.filter.UserFilterDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_RESPONSIBLE_USERS)
@RequestMapping("/api/v1/application-responsible-users")
public class ApplicationResponsibleUsersController extends BaseAccessController {

    private final ApplicationResponsibleUsersService applicationResponsibleUsersService;

    private final KeycloakUserService keycloakUserService;

    @Override
    public String getEditRole() {
        return SecurityRole.CORE_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }


    @GetMapping(value = "/by-application/{applicationId}")
    @ApiOperation(value = "select application responsible users")
    public List<ApplicationResponsibleUsersDTO> selectByApplicationId(@PathVariable Integer applicationId) {
        return applicationResponsibleUsersService.selectByApplicationId(applicationId);
    }

    @GetMapping(value = "/main-responsible-user/by-application/{applicationId}")
    @ApiOperation(value = "select main responsible user")
    public ApplicationResponsibleUsersDTO selectMainResponsibleUserByApplicationId(@PathVariable Integer applicationId) {
        return  applicationResponsibleUsersService.selectMainResponsibleUserByApplicationId(applicationId);
    }

    @GetMapping(value = "/autocomplete/{group}")
    @ApiOperation(value = "Select users by group")
    public List<NacidUserAutocompleteDTO> getResponsibleUsersByGroupAutocomplete(@PathVariable String group, @RequestParam(name = "selectedResponsibleUser", required = false) String selectedResponsibleUser) {
        List<NacidUserAutocompleteDTO> groupUsers = applicationResponsibleUsersService.selectResponsibleUsersByGroup(group);
        List<NacidUserAutocompleteDTO> modifiedGroupUsers = groupUsers.stream().collect(Collectors.toList());

        if (StringUtils.hasText(selectedResponsibleUser)) {
            boolean containsSelectedUser = groupUsers.stream().anyMatch(e -> e.getUsername().equals(selectedResponsibleUser));
            if (!containsSelectedUser) {
                NacidUserDetailsDTO currentResponsibleUser = keycloakUserService.getUserByUsername(selectedResponsibleUser);
                if (Objects.nonNull(currentResponsibleUser)) {
                    modifiedGroupUsers.add(NacidUserAutocompleteDTO.newInstance(currentResponsibleUser.getUsername(), currentResponsibleUser.getFullName(), false));
                }
            }
        }
        modifiedGroupUsers.add(0, NacidUserAutocompleteDTO.newInstance("-", "Без отговорник", true));

        return modifiedGroupUsers;
    }

    @GetMapping(value = "/by-username/{userName}")
    @ApiOperation(value = "Select all users")
    public BaseUserDetailsDTO getResponsibleUserByUsername(@PathVariable String userName) {
         return keycloakUserService.getUserByUsername(userName);
    }

    @GetMapping(value = "/by-username/{userName}/formatted-name")
    @ApiOperation(value = "Select user formatted name")
    public String getFormattedUserName(@PathVariable String userName) {
        NacidUserDetailsDTO userByUsername = keycloakUserService.getUserByUsername(userName);
        return userByUsername.getFullName().concat(" (").concat(userByUsername.getUsername()).concat(")");
    }

}
