package bg.duosoft.email.nacidemailproducer.filter;

import java.util.HashMap;
import java.util.Map;

public class EmailTemplateSorterUtils {

    public static final String NAME = "name";
    public static final String CREATED_DATE = "createdDate";
    public static final String LAST_UPDATE_DATE = "lastUpdateDate";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(NAME, "n.name");
        map.put(CREATED_DATE, "n.created_date");
        map.put(LAST_UPDATE_DATE, "n.last_update_date");
        return map;
    }

}
