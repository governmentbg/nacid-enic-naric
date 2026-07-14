package bg.duosoft.email.nacidemailproducer.filter;

import java.util.HashMap;
import java.util.Map;

public class EmailNotificationSorterUtils {

    public static final String CREATED_DATE = "createdDate";
    public static final String SENT_DATE = "sentDate";
    public static final String SUBJECT = "subject";
    public static final String RECIPIENTS = "recipients";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(CREATED_DATE, "n.created_date");
        map.put(SENT_DATE, "n.sent_date");
        map.put(SUBJECT, "n.subject");
        map.put(RECIPIENTS, "n.recipients");
        return map;
    }

}
