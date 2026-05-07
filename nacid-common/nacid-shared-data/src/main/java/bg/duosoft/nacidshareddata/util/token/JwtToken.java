package bg.duosoft.nacidshareddata.util.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtToken {

    @JsonProperty(value = "id_token")
    private String idToken;

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private Integer accessTokenExpiresIn;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "refresh_expires_in")
    private Integer refreshTokenExpiresIn;

    @JsonProperty(value = "session_state")
    private String sessionState;

    @JsonProperty(value = "scope")
    private String scope;

    private LocalDateTime createdDate;
    private LocalDateTime accessTokenExpirationDate;
    private LocalDateTime refreshTokenExpirationDate;

    public void overrideFields(JwtToken other) {
        if (Objects.nonNull(other)) {
            this.idToken = other.getIdToken();
            this.accessToken = other.getAccessToken();
            this.accessTokenExpiresIn = other.getAccessTokenExpiresIn();
            this.refreshToken = other.getRefreshToken();
            this.refreshTokenExpiresIn = other.getRefreshTokenExpiresIn();
            this.sessionState = other.getSessionState();
            this.scope = other.getScope();
            this.createdDate = other.getCreatedDate();
            this.accessTokenExpirationDate = other.getAccessTokenExpirationDate();
            this.refreshTokenExpirationDate = other.getRefreshTokenExpirationDate();
        } else {
            this.idToken = null;
            this.accessToken = null;
            this.accessTokenExpiresIn = null;
            this.refreshToken = null;
            this.refreshTokenExpiresIn = null;
            this.sessionState = null;
            this.scope = null;
            this.createdDate = null;
            this.accessTokenExpirationDate = null;
            this.refreshTokenExpirationDate = null;
        }
    }

    public boolean isAccessTokenExpired() {
        LocalDateTime expirationDate = this.getAccessTokenExpirationDate();
        LocalDateTime now = LocalDateTime.now();
        return expirationDate.isBefore(now);
    }

    public boolean isRefreshTokenExpired() {
        LocalDateTime expirationDate = this.getRefreshTokenExpirationDate();
        LocalDateTime now = LocalDateTime.now();
        return expirationDate.isBefore(now);
    }

}
