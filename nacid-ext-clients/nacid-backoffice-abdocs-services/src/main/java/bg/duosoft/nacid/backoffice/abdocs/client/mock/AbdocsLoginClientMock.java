package bg.duosoft.nacid.backoffice.abdocs.client.mock;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsLoginClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Profile({"dev", "office"})
@Component
@Primary
public class AbdocsLoginClientMock implements AbdocsLoginClient {
    @Override
    public SecurityToken getTokenByUsername(Map<String, ?> body) {
        SecurityToken token = new SecurityToken();
        token.setAccessToken("mock-dev-access-token-" + UUID.randomUUID());
        token.setAccessTokenExpiresIn(3600);
        token.setTokenType("Bearer");

        LocalDateTime now = LocalDateTime.now();
        token.setAccessTokenCreatedDate(now);
        token.setAccessTokenExpirationDate(now.plusSeconds(3600));

        return token;
    }
}
