package bg.duosoft.nacidshareddata.validation.base;

import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

public interface EikValidator<T> extends Validator<T> {
    int[] FIRST_SUM_9DIGIT_WEIGHTS = {1, 2, 3, 4, 5, 6, 7, 8};
    int[] SECOND_SUM_9DIGIT_WEIGHTS = {3, 4, 5, 6, 7, 8, 9, 10};
    int[] FIRST_SUM_13DIGIT_WEIGHTS = {2, 7, 3, 5};
    int[] SECOND_SUM_13DIGIT_WEIGHTS = {4, 9, 5, 7};

    default void validateEIK(List<ValidationError> errors, String eik, String pointer) {
        rejectIfEmptyString(errors, eik, pointer, "validation.field.required");

        if (StringUtils.hasText(eik)) {
            boolean isCorrectLength = eik.length() == 9 || eik.length() == 13;
            rejectIfFalse(errors, isCorrectLength, pointer, "validation.field.invalid");

            if (isCorrectLength) {
                int[] digits = checkInput(eik, eik.length());
                Integer lastCalculatedDigit = calculateLastDigit(digits);
                rejectIfFalse(errors, Objects.nonNull(lastCalculatedDigit) && lastCalculatedDigit.equals(digits[digits.length - 1]), pointer, "validation.field.invalid");
            }
        }
    }

    private static Integer calculateLastDigit(int[] digits) {
        switch (digits.length) {
            case 9:
                return calculateNinthDigitInEIK(digits);
            case 13:
                return calculateThirteenthDigitInEIK(digits);
        }

        return null;
    }

    private static int calculateNinthDigitInEIK(int[] digits) {
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum = sum + (digits[i] * FIRST_SUM_9DIGIT_WEIGHTS[i]);
        }
        int remainder = sum % 11;
        if (remainder != 10) {
            return remainder;
        }
        // remainder= 10
        int secondSum = 0;
        for (int i = 0; i < 8; i++) {
            secondSum = secondSum + (digits[i] * SECOND_SUM_9DIGIT_WEIGHTS[i]);
        }
        int secondRem = secondSum % 11;
        if (secondRem != 10) {
            return secondRem;
        }
        // secondRemainder= 10
        return 0;
    }

    private static int calculateThirteenthDigitInEIK(int[] digits) {
        int ninthDigit = calculateNinthDigitInEIK(digits);
        if (ninthDigit != digits[8]) {
            throw new IllegalArgumentException("Incorrect 9th digit in EIK-13.");
        }
        // 9thDigit is a correct checkSum. Continue with 13thDigit
        int sum = 0;
        for (int i = 8, j = 0; j < 4; i++, j++) {
            sum = sum + (digits[i] * FIRST_SUM_13DIGIT_WEIGHTS[j]);
        }
        int remainder = sum % 11;
        if (remainder != 10) {
            return remainder;
        }
        // remainder= 10
        int secondSum = 0;
        for (int i = 8, j = 0; j < 4; i++, j++) {
            secondSum = secondSum + (digits[i] * SECOND_SUM_13DIGIT_WEIGHTS[j]);
        }
        int secondRem = secondSum % 11;
        if (secondRem != 10) {
            return secondRem;
        }
        // secondRemainder= 10
        return 0;
    }

    private static int[] checkInput(String eik, int eikLength) {
        if (eik != null && eik.length() != eikLength) {
            throw new IllegalArgumentException("Incorrect count of digits in EIK: "
                    + eik.length() + "!= 9 or 13");
        }
        // eik.length= eikLength
        char[] charDigits = eik.toCharArray();
        int[] digits = new int[charDigits.length];
        for (int i = 0; i < digits.length; i++) {
            if (Character.isDigit(charDigits[i])) {
                digits[i] = Character.digit(charDigits[i], 10);
            } else {
                throw new IllegalArgumentException(
                        "Incorrect input character. Only digits are allowed.");
            }
        }
        return digits;
    }
}
