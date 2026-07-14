package bg.duosoft.nacidshareddata.util.security;


import bg.duosoft.nacidshareddata.property.KeycloakProperties;
import bg.duosoft.nacidshareddata.util.token.JwtToken;
import bg.duosoft.nacidshareddata.util.token.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 16:15
 */
@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private static final String KEYCLOAK_TOKEN_URI_PATTERN = "{providerBaseUri}/realms/{realmName}/protocol/openid-connect/token";

    private final WebClient webClient;
    private final KeycloakProperties keycloakProperties;
    private JwtToken jwtToken;

   public String getAccessToken(){
       if(jwtToken == null || !StringUtils.hasText(jwtToken.getAccessToken()) || jwtToken.isAccessTokenExpired()){
           jwtToken = getTokenByClientCredential();
           if(jwtToken != null && StringUtils.hasText(jwtToken.getAccessToken())){
               return jwtToken.getAccessToken();
           }
       } else {
           return jwtToken.getAccessToken();
       }
       throw new RuntimeException("Can not return access token");
   }

    private JwtToken getTokenByClientCredential() {
        try {
            String result = executeTokenRequest(buildClientCredentialFormData());
            if (!StringUtils.hasText(result)) {
                return null;
            }
            return JwtUtils.buildJwtTokenFromString(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private String executeTokenRequest(MultiValueMap<String, String> formData) {
        return webClient
                .post()
                .uri(getKeycloakTokenUri())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private MultiValueMap<String, String> buildClientCredentialFormData() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", keycloakProperties.getKeycloakClientId());
        formData.add("client_secret", keycloakProperties.getKeycloakClientSecret());
        formData.add("grant_type", "client_credentials");
        return formData;
    }

    public String getKeycloakTokenUri() {
        return KEYCLOAK_TOKEN_URI_PATTERN.replace("{providerBaseUri}",
                keycloakProperties.getKeycloakProviderBaseUri()).replace("{realmName}",
                keycloakProperties.getKeycloakProviderRealmName());
    }
}
