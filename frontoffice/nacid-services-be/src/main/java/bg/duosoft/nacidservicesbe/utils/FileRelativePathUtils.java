package bg.duosoft.nacidservicesbe.utils;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 15:37
 */
public class FileRelativePathUtils {

    public static String createRelativeFilePath(Integer id, LocalDate appDateCreated){
        int year = appDateCreated.getYear();
        int month = appDateCreated.getMonthValue();
        return String.format("%s/%s/%s", year, month, id);
    }
}
