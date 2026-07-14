package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.TextSearchType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseRepositoryCustomImpl {

    @PersistenceContext
    protected EntityManager em;

    protected static String generateTextSearchSqlByValueAndSearchType(String value, TextSearchType textSearchType, String sqlFieldName, String sqlParameterName, Map<String, Object> queryParameters) {
        if (textSearchType == null) {
            textSearchType = TextSearchType.CONTAINS_WORDS;
        }
        switch (textSearchType) {
            case EXACTLY -> {
                String res = "( lower(" + sqlFieldName + ") like lower(:" + sqlParameterName + "))";
                res = String.format("(lower(%s) like lower(:%s))", sqlFieldName, sqlParameterName);
                queryParameters.put(sqlParameterName, value);
                return res;
            }
            case WHOLE_WORDS -> {
                String[] parts = value.split(" ");
                Integer cnt = 1;
                List<String> res = new ArrayList<>();
                for (String p : parts) {
                    List<String> sub = new ArrayList<>();
                    String param = sqlParameterName + (cnt * 10 + 1);
                    sub.add(String.format("lower(%s) like lower(:%s)", sqlFieldName, param));
                    queryParameters.put(param, p + " %");

                    param = sqlParameterName + (cnt * 10 + 2);
                    sub.add(String.format("lower(%s) like lower(:%s)", sqlFieldName, param));
                    queryParameters.put(param, "% " + p + " %");

                    param = sqlParameterName + (cnt * 10 + 3);
                    sub.add(String.format("lower(%s) like lower(:%s)", sqlFieldName, param));
                    queryParameters.put(param, "% " + p);

                    res.add(sub.stream().collect(Collectors.joining(") OR (", "(", ")")));
                    cnt++;
                }
                return "(" + res.stream().collect(Collectors.joining(") AND (", "(", ")")) + ")";
            }
            case CONTAINS_WORDS -> {
                String[] parts = value.split(" ");
                Integer cnt = 1;
                List<String> res = new ArrayList<>();
                for (String p : parts) {
                    String param = sqlParameterName + cnt;
                    res.add(String.format("lower(%s) like lower(:%s)", sqlFieldName, param));
                    queryParameters.put(param, "%" + p + "%");
                    cnt++;
                }
                return "(" + res.stream().collect(Collectors.joining(") AND (", "(", ")")) + ")";
            }
            default -> throw new RuntimeException("Unknown text search type: " + textSearchType);
        }
    }
}
