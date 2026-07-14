package bg.duosoft.nacid.backoffice.abdocs.config.security;

import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class SecurityTokenHolder {

    private static class InstanceHolder {
        private static final SecurityTokenHolder INSTANCE = new SecurityTokenHolder(new HashMap<>());
    }

    private final Map<String, SecurityToken> tokenMap;

    private SecurityTokenHolder(Map<String, SecurityToken> tokenMap) {
        this.tokenMap = tokenMap;
    }

    public static SecurityTokenHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public String getAccessToken(String username) {
        SecurityToken securityToken = tokenMap.get(username);
        if (Objects.isNull(securityToken) || securityToken.isAccessTokenExpired()) {
            return null;
        }

        return securityToken.getAccessToken();
    }

    public void addToken(String username, SecurityToken token) {
        tokenMap.put(username, token);
    }

    public void removeTokenByValue(String token) {
        List<String> keys = tokenMap
                .entrySet()
                .stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .filter(entry -> entry.getValue().getAccessToken().equals(token))
                .map(Map.Entry::getKey)
                .toList();

        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {
                tokenMap.remove(key);
            }
        }
    }

    public void clearMap() {
        tokenMap.clear();
    }

}
