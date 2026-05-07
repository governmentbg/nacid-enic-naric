package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class RegprofReportSortUtils {
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
