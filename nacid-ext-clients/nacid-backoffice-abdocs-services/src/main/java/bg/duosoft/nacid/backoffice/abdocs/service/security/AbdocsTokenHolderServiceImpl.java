
package bg.duosoft.nacid.backoffice.abdocs.service.security;

import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;
import bg.duosoft.nacid.backoffice.abdocs.config.security.SecurityTokenHolder;
import bg.duosoft.nacid.backoffice.abdocs.properties.AbdocsPropertyAccess;
import bg.duosoft.nacid.backoffice.abdocs.service.login.AbdocsLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsTokenHolderServiceImpl implements AbdocsTokenHolderService {

    private final AbdocsLoginService abdocsLoginService;
    private final AbdocsPropertyAccess abdocsPropertyAccess;

    @Override
    public String selectAccessToken(String username) {
        SecurityTokenHolder tokensHolder = SecurityTokenHolder.getInstance();
        String accessToken = tokensHolder.getAccessToken(username);
        if (!StringUtils.hasText(accessToken)) {
            SecurityToken securityToken = abdocsLoginService.selectToken(username);
            if (Objects.nonNull(securityToken)) {
                LocalDateTime createdDate = LocalDateTime.now();
                securityToken.setAccessTokenCreatedDate(createdDate);
                securityToken.setAccessTokenExpirationDate(createdDate.plusSeconds(securityToken.getAccessTokenExpiresIn()).minusSeconds(10));

                tokensHolder.addToken(username, securityToken);
                return securityToken.getAccessToken();
            }
        }

        return accessToken;
    }

    @Override
    public String selectAdminAccessToken() {
        return selectAccessToken(abdocsPropertyAccess.getAbdocsAdminUser());
    }

}
