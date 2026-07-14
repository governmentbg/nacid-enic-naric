package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class RegprofApplicationsSortUtils {
    public static final String ID = "id";
    public static final String ENTRY_NUM = "entryNum";
    public static final String ENTRY_DATE = "entryDate";
    public static final String END_DATE = "endDate";
    public static final String APPLICANT_NAME = "applicantName";
    public static final String APN_STATUS_NAME = "apnStatusName";
    public static final String DOCFLOW_STATUS_NAME = "docflowStatusName";
    public static final String SESSION_STATUS_NAME = "sessionStatusName";
    public static final String RESPONSIBLE_USER_NAME = "responsibleUserName";
    public static final String BACKOFFICE_DATE = "backofficeDate";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "r.id");
        map.put(ENTRY_NUM, "r.entryNum");
        map.put(ENTRY_DATE, "r.entryDate");
        map.put(BACKOFFICE_DATE, "r.backofficeDate");
        map.put(END_DATE, "r.endDate");
        map.put(APPLICANT_NAME, "r.applicantName");
        map.put(APN_STATUS_NAME, "r.apnStatusName");
        map.put(DOCFLOW_STATUS_NAME, "r.docflowStatusName");
        map.put(SESSION_STATUS_NAME, "r.status.name");
        map.put(RESPONSIBLE_USER_NAME, "r.responsibleUserName");
        return map;
    }
}
