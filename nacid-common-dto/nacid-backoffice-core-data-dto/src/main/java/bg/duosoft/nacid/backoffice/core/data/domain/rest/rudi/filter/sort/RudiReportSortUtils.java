package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 07.09.2023
 * Time: 11:39
 */
public class RudiReportSortUtils {
    private static Map<String, String> map = new HashMap<>();
    static {
        map.put("id", "base.id");
        map.put("entryNum", "base.entry_num");
        map.put("entryDate", "base.entry_date");
        map.put("backofficeDate", "base.backoffice_date");
    }
    public static Map<String, String> sorterColumnMap() {
        return map;
    }

}
