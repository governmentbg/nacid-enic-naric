package bg.duosoft.nacidminioservices.utils;

import bg.duosoft.nacidshareddata.util.random.RandomBase64Util;

import java.util.UUID;

public class MinioIdGenerator {

    public static String generateFileId() {
        String uuid = UUID.randomUUID().toString();
        String secret = RandomBase64Util.generateBase64UrlEncodedRandomString(16);
        return uuid + secret;
    }

}
