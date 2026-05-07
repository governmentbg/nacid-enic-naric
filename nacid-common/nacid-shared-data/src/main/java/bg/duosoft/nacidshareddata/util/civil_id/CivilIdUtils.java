package bg.duosoft.nacidshareddata.util.civil_id;

import bg.duosoft.nacidshareddata.util.integer.IntegerUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static bg.duosoft.nacidshareddata.util.integer.IntegerUtils.parseInteger;

public class CivilIdUtils {

    public static boolean validateEGN(String input) {
        if (input == null) {
            return false;
        } else if (input.length() != 10) {
            return false;
        } else {

            int[] digits = new int[10];
            int[] coeffs = {2, 4, 8, 5, 10, 9, 7, 3, 6};
            int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


            for (int i = 0; i < input.length(); i++) {
                Integer digit = parseInteger(input.charAt(i) + "", null);
                if (digit == null) {
                    break;
                }
                digits[i] = digit;
            }

            if (10 != digits.length) {
                return false;
            }

            int dd = digits[4] * 10 + digits[5];
            int mm = digits[2] * 10 + digits[3];
            int yy = digits[0] * 10 + digits[1];
            Integer yyyy = null;

            if (mm >= 1 && mm <= 12) {
                yyyy = 1900 + yy;
            } else if (mm >= 21 && mm <= 32) {
                mm -= 20;
                yyyy = 1800 + yy;
            } else if (mm >= 41 && mm <= 52) {
                mm -= 40;
                yyyy = 2000 + yy;
            } else {
                return false;
            }

            days[1] += isLeapYear(yyyy) ? 1 : 0;

            if (!(dd >= 1 && dd <= days[mm - 1])) {
                return false;
            }

            // Gregorian calendar adoption. 31 Mar 1916 was followed by 14 Apr 1916.
            if (yyyy == 1916 && mm == 4 && (dd >= 1 && dd < 14)) {
                return false;
            }

            int checksum = 0;

            for (int j = 0; j < coeffs.length; j++) {
                checksum = checksum + (digits[j] * coeffs[j]);
            }
            checksum %= 11;
            if (10 == checksum) {
                checksum = 0;
            }

            if (digits[9] != checksum) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateLNCH(String input) {
        if (input == null) {
            return false;
        } else if (input.length() != 10) {
            return false;
        } else {

            int[] digits = new int[10];
            int[] coeffs = {21, 19, 17, 13, 11, 9, 7, 3, 1};
            int i;
            for (i = 0; i < input.length(); i++) {
                Integer digit = IntegerUtils.parseInteger(input.charAt(i) + "", null);
                if (digit == null) {
                    break;
                }
                digits[i] = digit;
            }

            if (10 != i) {
                return false;
            }

            int checksum = 0;

            for (int j = 0; j < coeffs.length; j++) {
                checksum = checksum + (digits[j] * coeffs[j]);
            }
            checksum %= 10;

            if (digits[9] != checksum) {
                return false;
            }
        }
        return true;
    }

    public static LocalDate getBirthDate(String egn) throws IllegalArgumentException {
        if (!validateEGN(egn)) {
            throw new IllegalArgumentException("Invalid EGN");
        }
        int gg = Integer.parseInt(egn.substring(0, 2));
        int mm = Integer.parseInt(egn.substring(2, 4));
        int dd = Integer.parseInt(egn.substring(4, 6));

        if (dd < 1 || dd > 31) {
            throw new IllegalArgumentException("Invalid day of month in position 5 or 6");
        }
        final int month;
        final int year;
        if (mm >= 1 && mm <= 12) {
            year = 1900 + gg;
            month = mm;
        } else if (mm >= 21 && mm <= 32) {
            year = 1800 + gg;
            month = mm - 20;
        } else if (mm >= 41 && mm <= 52) {
            year = 2000 + gg;
            month = mm - 40;
        } else {
            throw new IllegalArgumentException("Invalid month in position 3 or 4.");
        }
        YearMonth ym = YearMonth.of(year, month);
        if (dd > ym.atEndOfMonth().getDayOfMonth()) {
            throw new IllegalArgumentException(String.format("The last day of the month %s, year %s is %s, but not %s", month + 1, year, ym.atEndOfMonth().getDayOfMonth(), dd));
        }
        return LocalDate.of(year, month, dd);
    }

    private static boolean isLeapYear(int yyyy) {
        if (yyyy % 400 == 0) {
            return true;
        }
        if (yyyy % 100 == 0) {
            return false;
        }
        if (yyyy % 4 == 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(validateLNCH("1001968542"));
    }

}
