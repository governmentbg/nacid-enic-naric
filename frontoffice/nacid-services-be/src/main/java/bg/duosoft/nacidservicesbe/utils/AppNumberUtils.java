package bg.duosoft.nacidservicesbe.utils;

import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import bg.duosoft.nacidshareddata.util.random.RandomBase64Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.01.2023
 * Time: 15:54
 */
public class AppNumberUtils {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DTOConstants.DATE_FORMAT);

    public static String generateTempApplicationNumber(String sequence, Integer id){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        return String.format("%s%s%010d", sequence, year, id);
    }

    public static String generateApplicationAccessCode(){
        return RandomBase64Util.generateBase64UrlEncodedRandomString(16);
    }

    public static Object[] breakDossierNumber(String dossierNumber){
        String[] parts = dossierNumber.split("/");
        LocalDate entryDate = LocalDate.parse(parts[1], formatter);
        return new Object[]{parts[0], entryDate};
    }
}
