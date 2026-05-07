
package bg.duosoft.nacid.backoffice.abdocs.service.login;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsLoginClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;
import bg.duosoft.nacid.backoffice.abdocs.properties.AbdocsPropertyAccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsLoginServiceImpl implements AbdocsLoginService {

    private final AbdocsLoginClient abdocsLoginClient;
    private final AbdocsPropertyAccess abdocsPropertyAccess;

    @Override
    public SecurityToken selectToken(String username) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("grant_type", "password");
            map.put("ticketUsername", username);
            map.put("username", abdocsPropertyAccess.getAbdocsTokenAuthUser());
            map.put("password", abdocsPropertyAccess.getAbdocsTokenAuthPassword());
            return abdocsLoginClient.getTokenByUsername(map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
