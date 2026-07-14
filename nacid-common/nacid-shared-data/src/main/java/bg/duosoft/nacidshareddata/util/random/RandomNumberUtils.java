package bg.duosoft.nacidshareddata.util.random;

import java.security.SecureRandom;
import java.util.Random;

public class RandomNumberUtils {

    public static String generateEightDigitsRandomString() {
        return String.valueOf(generateEightDigitsRandom());
    }

    public static int generateEightDigitsRandom() {
        return generateRandomInt(10_000_000, 99_999_999);
    }

    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomDigitAndSymbolsString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomDigitAndSymbolsString(12));
    }

}

