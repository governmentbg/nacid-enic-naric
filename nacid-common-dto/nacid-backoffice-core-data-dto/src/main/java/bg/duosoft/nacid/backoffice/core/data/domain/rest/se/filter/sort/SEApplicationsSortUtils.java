package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class SEApplicationsSortUtils {

    public static final String ID = "id";
    public static final String ENTRY_NUM = "entryNum";
    public static final String ENTRY_DATE = "entryDate";
    public static final String APPLICANT_NAME = "applicantName";
    public static final String REPRESENTATIVE_NAME = "representativeName";
    public static final String SCHOOL_NAME = "schoolName";
    public static final String SCHOOL_COUNTRY = "schoolCountry";
    public static final String SCHOOL_GRADING_SCALE_COUNTRY = "schoolGradingScaleCountry";
    public static final String DIPLOMA_OWNER_NAME = "diplomaOwnerName";
    public static final String UNIVERSITY_NAME = "universityName";
    public static final String UNIVERSITY_COUNTRY_NAME = "universityCountryName";
    public static final String EDU_LEVEL_NAME = "eduLevelName";
    public static final String SPECIALITY_NAME = "specialityName";
    public static final String APN_STATUS_NAME = "apnStatusName";
    public static final String DOCFLOW_STATUS_NAME = "docflowStatusName";
    public static final String RECOGNIZED_QUALIFICATION = "recognizedQualification";
    public static final String RECOGNIZED_PROF_GROUP_NAME = "recognizedProfGroupName";
    public static final String SESSION_STATUS_NAME = "sessionStatusName";
    public static final String CALENDAR_SESSION_STATUS_NAME = "calendarSessionStatusName";
    public static final String SESSION_NUM = "sessionNum";
    public static final String SESSION_TIME = "sessionTime";
    public static final String RESPONSIBLE_USER_NAME = "responsibleUserName";
    public static final String BACKOFFICE_DATE = "backofficeDate";
    public static final String SESSION_DATE = "sessionDate";



    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "r.id");
        map.put(ENTRY_NUM, "r.entryNum");
        map.put(ENTRY_DATE, "r.entryDate");
        map.put(BACKOFFICE_DATE, "r.backofficeDate");
        map.put(APPLICANT_NAME, "r.applicantName");
        map.put(REPRESENTATIVE_NAME, "r.representativeName");
        map.put(SCHOOL_NAME, "r.schoolName");
        map.put(SCHOOL_COUNTRY, "r.schoolCountryName");
        map.put(SCHOOL_GRADING_SCALE_COUNTRY, "r.schoolGradingScaleCountryName");
        map.put(DIPLOMA_OWNER_NAME, "r.diplomaOwnerName");
        map.put(UNIVERSITY_NAME, "r.universityName");
        map.put(UNIVERSITY_COUNTRY_NAME, "r.universityCountryName");
        map.put(EDU_LEVEL_NAME, "r.eduLevelName");
        map.put(SPECIALITY_NAME, "r.specialityName");
        map.put(APN_STATUS_NAME, "r.apnStatusName");
        map.put(DOCFLOW_STATUS_NAME, "r.docflowStatusName");
        map.put(RECOGNIZED_QUALIFICATION, "r.recognizedQualification");
        map.put(RECOGNIZED_PROF_GROUP_NAME, "r.recognizedProfGroupName");
        map.put(SESSION_STATUS_NAME, "r.status.name");
        map.put(CALENDAR_SESSION_STATUS_NAME, "r.statusName");
        map.put(SESSION_NUM, "r.sessionNum");
        map.put(SESSION_TIME, "r.sessionTime");
        map.put(SESSION_DATE, "r.sessionDate");
        map.put(RESPONSIBLE_USER_NAME, "r.responsibleUserName");
        return map;
    }
}
