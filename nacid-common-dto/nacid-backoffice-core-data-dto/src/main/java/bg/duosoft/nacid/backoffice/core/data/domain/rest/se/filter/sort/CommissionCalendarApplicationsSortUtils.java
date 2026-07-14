package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class CommissionCalendarApplicationsSortUtils {
    public static final String ID = "id";
    public static final String ENTRY_NUM = "entryNum";
    public static final String ENTRY_DATE = "entryDate";
    public static final String APPLICANT_NAME = "applicantName";
    public static final String APPLICATION_TYPE_NAME = "applicationTypeName";
    public static final String APPLICATION_SUBTYPE_NAME = "applicationSubtypeName";
    public static final String SCHOOL_NAME = "schoolName";
    public static final String SCHOOL_COUNTRY_NAME = "schoolCountryName";
    public static final String STATUS_NAME = "statusName";
    public static final String ATE_CODE = "ateCode";
    public static final String ASE_CODE = "aseCode";
    public static final String RESPONSIBLE_USER = "responsibleUser";
    public static final String SCHOOL_GRADING_SCALE_COUNTRY_CODE = "schoolGradingScaleCountryCode";
    public static final String GRADING_SCALE_COUNTRY_NAME = "gradingScaleCountryName";
    public static final String INTERNATIONAL_GRADING_SYSTEM = "internationalGradingSystem";
    public static final String GRADING_SYSTEM_NAME = "gradingSystemName";

    private CommissionCalendarApplicationsSortUtils() {
    }

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "v.id");
        map.put(ENTRY_NUM, "v.entryNum");
        map.put(ENTRY_DATE, "v.entryDate");
        map.put(APPLICANT_NAME, "v.applicantName");
        map.put(APPLICATION_TYPE_NAME, "v.applicationTypeName");
        map.put(APPLICATION_SUBTYPE_NAME, "v.applicationSubtypeName");
        map.put(SCHOOL_NAME, "v.schoolName");
        map.put(SCHOOL_COUNTRY_NAME, "v.schoolCountryName");
        map.put(STATUS_NAME, "v.statusName");
        map.put(ATE_CODE, "v.ateCode");
        map.put(ASE_CODE, "v.aseCode");
        map.put(RESPONSIBLE_USER, "v.responsibleUser");
        map.put(SCHOOL_GRADING_SCALE_COUNTRY_CODE, "v.schoolGradingScaleCountryCode");
        map.put(GRADING_SCALE_COUNTRY_NAME, "v.gradingScaleCountryName");
        map.put(INTERNATIONAL_GRADING_SYSTEM, "v.internationalGradingSystem");
        map.put(GRADING_SYSTEM_NAME, "v.gradingSystemName");
        return map;
    }

    public static boolean isValidSortColumn(String sortColumn) {
        return sorterColumnMap().containsKey(sortColumn);
    }

    public static String getSortField(String sortColumn) {
        return sorterColumnMap().get(sortColumn);
    }
}
