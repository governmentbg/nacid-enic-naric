package bg.duosoft.nacid.backoffice.abdocs.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2022
 * Time: 11:56
 */
public class AbdocsServiceSecurityUtils {

    public static final String USERNAME_CLAIM = "preferred_username";
    public static final String FIRST_NAME_CLAIM = "given_name";
    public static final String LAST_NAME_CLAIM = "family_name";
    public static final String EMAIL_CLAIM = "email";
    public static final String SUB_CLAIM = "sub";

    public static boolean isUserAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt) {
            return true;
        }
        return false;
    }

    public static Object getClaim(String claim) {
        if (isUserAuthenticated()) {
            Jwt jwt = getJwt();
            if (jwt != null) {
                return jwt.getClaims().get(claim);
            }
        }
        return null;
    }

    public static String getUsername() {
        return (String) getClaim(USERNAME_CLAIM);
    }

    public static String getEmail() {
        return (String) getClaim(EMAIL_CLAIM);
    }

    public static String getAccessToken() {
        if (isUserAuthenticated()) {
            return getJwt().getTokenValue();
        }
        return null;
    }

    public static Jwt getJwt() {
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }




}
