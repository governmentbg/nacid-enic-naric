package bg.duosoft.nacid.backoffice.abdocs.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityToken {
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private Integer accessTokenExpiresIn;

    @JsonProperty(value = "token_type")
    private String tokenType;

    private LocalDateTime accessTokenCreatedDate;
    private LocalDateTime accessTokenExpirationDate;

    public boolean isAccessTokenExpired() {
        LocalDateTime expirationDate = this.getAccessTokenExpirationDate();
        LocalDateTime now = LocalDateTime.now();
        return expirationDate.isBefore(now);
    }

}
