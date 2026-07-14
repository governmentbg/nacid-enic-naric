package bg.duosoft.nacid.backoffice.core.data.util.abdocs;

import bg.duosoft.nacidshareddata.util.date.DateUtils;
import org.springframework.data.util.Pair;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

public class AbdocsNumbersUtils {

    public static Pair<String, LocalDate> extractEntryNumberAndDate(String regNumber) {
        if (!StringUtils.hasText(regNumber)) {
            throw new RuntimeException("Cannot extract entry number and entry date, because registration number is empty !");
        }

        String[] split = regNumber.split("/");
        if (split.length != 2) {
            throw new RuntimeException("Cannot extract entry number and entry date, because of wrong reg number format! Registration number: " + regNumber);
        }

        String number = split[0];
        if (!StringUtils.hasText(number)) {
            throw new RuntimeException("Cannot extract entry number! Registration number: " + regNumber);
        }

        String date = split[1];
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        if (Objects.isNull(date)) {
            throw new RuntimeException("Cannot extract entry date ! Registration number: " + regNumber);
        }

        return Pair.of(number, localDate);
    }

    public static String buildRegistrationNumber(String entryNumber, LocalDate entryDate) {
        if (!StringUtils.hasText(entryNumber)) {
            throw new RuntimeException("Cannot build abdocs registration number! Entry number is empty!");
        }

        if (Objects.isNull(entryDate)) {
            throw new RuntimeException("Cannot build abdocs registration number! Entry date is empty!");
        }

        return entryNumber + "/" + DateUtils.formatLocalDate(entryDate);
    }

}
