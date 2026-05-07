package bg.duosoft.nacid.backoffice.abdocs.config.security.decoder;

import bg.duosoft.nacid.backoffice.abdocs.config.security.SecurityTokenHolder;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.util.StringUtils;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);

        int status = response.status();
        if (status == 401) {
            removeUserToken(response);
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request());
        }
        return exception;
    }

    private static void removeUserToken(Response response) {
        String bearerToken = response.request().headers().get("Authorization").stream().findFirst().orElse(null);
        if (StringUtils.hasText(bearerToken)) {
            String token = bearerToken.replace("Bearer ", "");
            SecurityTokenHolder.getInstance().removeTokenByValue(token);
        }
    }

}