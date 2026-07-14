package bg.duosoft.nacidcoreapi.controller.v1.common;

import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.07.2022
 * Time: 10:50
 */
@RestController
@RequestMapping("/api/v1/nacid-user-details")
@Slf4j
@RequiredArgsConstructor
public class NacidUserDetailsController {

    private final KeycloakUserService keycloakUserService;

    @GetMapping
    public NacidUserDetailsDTO getCurrentNacidUserDetails(){
        if(SecurityUtils.isUserAuthenticated()){
           return getNacidUserDetailsForUsernameInternal(SecurityUtils.getUsername());
        } else {
            return null;
        }
    }

    @GetMapping("/for-username/{username}")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).USERS_ACCESS)")
    public NacidUserDetailsDTO getNacidUserDetailsForUsername(@PathVariable String username){
        return getNacidUserDetailsForUsernameInternal(username);
    }

    @GetMapping("/for-email/{email}")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).USERS_ACCESS)")
    public NacidUserDetailsDTO getNacidUserDetailsForEmail(@PathVariable String email){
        return getNacidUserDetailsForEmailInternal(email);
    }

    private NacidUserDetailsDTO getNacidUserDetailsForUsernameInternal(String username){
        NacidUserDetailsDTO nacidUser = keycloakUserService.getUserByUsername(username);
        return nacidUser;
    }

    private NacidUserDetailsDTO getNacidUserDetailsForEmailInternal(String email){
        NacidUserDetailsDTO nacidUser = keycloakUserService.getUserByEmail(email);
        return nacidUser;
    }
}
