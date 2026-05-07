package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class ReportSqlSortFields {
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String SQL_EXPRESSION = "sqlExpression";
    public static final String MANY_ROWS_FLAG = "manyRowsFlag";
    public static final String GROUP_FLAG = "groupFlag";
    public static final String START_TEXT = "startText";
    public static final String END_TEXT = "endText";
    public static final String SEPARATOR_TEXT = "separatorText";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "r.id");
        map.put(DESCRIPTION, "r.description");
        map.put(SQL_EXPRESSION, "r.sqlExpression");
        map.put(MANY_ROWS_FLAG, "r.manyRowsFlag");
        map.put(GROUP_FLAG, "r.groupFlag");
        map.put(START_TEXT, "r.startText");
        map.put(END_TEXT, "r.endText");
        map.put(SEPARATOR_TEXT, "r.separatorText");
        return map;
    }
}
