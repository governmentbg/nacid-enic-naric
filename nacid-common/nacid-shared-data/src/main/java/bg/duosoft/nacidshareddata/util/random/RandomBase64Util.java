package bg.duosoft.nacidshareddata.util.random;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 09.01.2023
 * Time: 13:26
 */
public class RandomBase64Util {

    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public static String generateBase64UrlEncodedRandomString(int length){
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[length];
        secureRandom.nextBytes(secretBytes);
        String secret = encoder.encodeToString(secretBytes);
        return secret;
    }
}
