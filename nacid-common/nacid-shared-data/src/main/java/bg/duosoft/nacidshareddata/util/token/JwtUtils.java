package bg.duosoft.nacidshareddata.util.token;

import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.util.json.JsonUtil;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

@Slf4j
public class JwtUtils {

    public static final String EXP_DATE_CLAIM = "exp";
    public static final String CREATED_DATE_CLAIM = "iat";

    public static JWT generateJwt(String token) {
        try {
            String[] split_string = token.split("\\.");
            String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];
            String base64EncodedSignature = split_string[2];

            SignedJWT jwt = new SignedJWT(Base64URL.from(base64EncodedHeader), Base64URL.from(base64EncodedBody), Base64URL.from(base64EncodedSignature));
            if (Objects.isNull(jwt.getJWTClaimsSet())) {
                throw new RuntimeException("Jwt claim set is empty! Token: " + token);
            }
            return jwt;
        } catch (Exception e) {
            throw new RuntimeException("Token: " + token + "\n" + e.getMessage(), e);
        }
    }

    public static JwtToken buildJwtTokenFromString(String string) {
        JwtToken jwtToken = JsonUtil.readJson(string, JwtToken.class);

        JWT accessTokenJwt = JwtUtils.generateJwt(jwtToken.getAccessToken());
        try {
            String refreshToken = jwtToken.getRefreshToken();
            if (StringUtils.hasText(refreshToken)) {
                JWT refreshTokenJwt = JwtUtils.generateJwt(jwtToken.getRefreshToken());
                Date refreshTokenExpirationDate = (Date) refreshTokenJwt.getJWTClaimsSet().getClaim(JwtUtils.EXP_DATE_CLAIM);
                jwtToken.setRefreshTokenExpirationDate(DateUtils.convertToLocalDateTime(refreshTokenExpirationDate));
            }

            Date accessTokenCreatedDate = (Date) accessTokenJwt.getJWTClaimsSet().getClaim(JwtUtils.CREATED_DATE_CLAIM);
            jwtToken.setCreatedDate(DateUtils.convertToLocalDateTime(accessTokenCreatedDate));

            Date accessTokenExpirationDate = (Date) accessTokenJwt.getJWTClaimsSet().getClaim(JwtUtils.EXP_DATE_CLAIM);
            jwtToken.setAccessTokenExpirationDate(DateUtils.convertToLocalDateTime(accessTokenExpirationDate));
            return jwtToken;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
