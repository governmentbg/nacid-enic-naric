package bg.duosoft.nacid.backoffice.abdocs.client;

import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Profile({"stage", "production"})
@FeignClient(name = "AbdocsLoginClient", url = "${feign.abdocs-api.base-url}")
public interface AbdocsLoginClient {

    @PostMapping(value = "/keycloak/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    SecurityToken getTokenByUsername(@RequestBody Map<String, ?> body);

}
