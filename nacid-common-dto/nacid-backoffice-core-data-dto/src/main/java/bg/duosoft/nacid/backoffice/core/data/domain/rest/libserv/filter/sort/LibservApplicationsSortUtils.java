package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class LibservApplicationsSortUtils {

    public static final String ID = "id";
    public static final String APPLICANT_TITLE_AFTER = "applicantTitleAfter";
    public static final String APPLICANT_TITLE_BEFORE = "applicantTitleBefore";
    public static final String ENTRY_DATE = "entryDate";
    public static final String ENTRY_NUM = "entryNum";
    public static final String STATUS_NAME = "apnStatusName";
    public static final String DOCFLOW_STATUS_NAME = "docflowStatusName";
    public static final String APPLICANT_NAME = "applicantName";
    public static final String KEYWORDS = "brKeywords";
    public static final String PERIOD_RET_FROM = "brPeriodRetFrom";
    public static final String PERIOD_RET_TO = "brPeriodRetTo";
    public static final String SUBJECT = "brSubject";
    public static final String RESULT_KIND_NAME = "brResultKindName";
    public static final String SEARCH_TYPE_NAME = "brSearchTypeName";
    public static final String INQUIRY_KIND_NAME = "inquiryKindName";
    public static final String INQUIRY_AIM = "inquiryAim";
    public static final String INQUIRY_PERIOD_FROM = "inquiryPeriodFrom";
    public static final String INQUIRY_PERIOD_TO = "inquiryPeriodTo";
    public static final String PREVIOUS_INQUIRY = "previousInquiry";
    public static final String OFFICIAL_NOTE_KIND_NAME = "officialNoteKindName";


    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "r.id");
        map.put(KEYWORDS, "r.brKeywords");
        map.put(APPLICANT_TITLE_AFTER, "r.applicantTitleAfter");
        map.put(APPLICANT_TITLE_BEFORE, "r.applicantTitleBefore");
        map.put(ENTRY_DATE, "r.entryDate");
        map.put(ENTRY_NUM, "r.entryNum");
        map.put(APPLICANT_NAME, "r.applicantName");
        map.put(STATUS_NAME, "r.apnStatusName");
        map.put(DOCFLOW_STATUS_NAME, "r.docflowStatusName");
        map.put(PERIOD_RET_FROM, "r.brPeriodRetFrom");
        map.put(PERIOD_RET_TO, "r.brPeriodRetTo");
        map.put(RESULT_KIND_NAME, "r.brResultKindName");
        map.put(SEARCH_TYPE_NAME, "r.brSearchTypeName");
        map.put(SUBJECT, "r.brSubject");
        map.put(INQUIRY_KIND_NAME, "r.inquiryKindName");
        map.put(INQUIRY_AIM, "r.inquiryAim");
        map.put(INQUIRY_PERIOD_FROM, "r.inquiryPeriodFrom");
        map.put(INQUIRY_PERIOD_TO, "r.inquiryPeriodTo");
        map.put(PREVIOUS_INQUIRY, "r.previousInquiry");
        map.put(OFFICIAL_NOTE_KIND_NAME, "r.officialNoteKindName");
        return map;
    }
}
