package bg.duosoft.nacidshared.web.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AjpProperties {

    @Value("${tomcat.ajp.port:#{0}}")
    private int ajpPort;

    @Value("${tomcat.ajp.remoteauthentication:#{null}}")
    private String remoteAuthentication;

    @Value("${tomcat.ajp.enabled:#{false}}")
    private boolean tomcatAjpEnabled;

}
