
package bg.duosoft.nacid.backoffice.abdocs.properties;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class AbdocsPropertyAccess {

    @Value("${abdocs.admin-user}")
    private String abdocsAdminUser;

    @Value("${abdocs.token-auth.user}")
    private String abdocsTokenAuthUser;

    @Value("${abdocs.token-auth.password}")
    private String abdocsTokenAuthPassword;

    @Value("${feign.abdocs-api.base-url}")
    private String abdocsApiBaseUrl;

    @Value("${abdocs.base-url}")
    private String abdocsBaseUrl;

    @Value("${ras.base-url}")
    private String rasBaseUrl;

    @Value("${apostille.base-url}")
    private String apostilleBaseUrl;
}
