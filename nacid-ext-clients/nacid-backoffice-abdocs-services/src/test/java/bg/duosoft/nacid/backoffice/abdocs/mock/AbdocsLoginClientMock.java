package bg.duosoft.nacid.backoffice.abdocs.mock;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsLoginClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Profile("test")
@Component
@Primary
public class AbdocsLoginClientMock implements AbdocsLoginClient {
    @Override
    public SecurityToken getTokenByUsername(Map<String, ?> body) {
        return new SecurityToken();
    }
}
