package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 20:54
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FieldSqlExecutor extends SqlExecutorBase{


    public Set<String> getFieldNames(String[] fieldNames, String[] groupNames) {
        return _getFieldNames(fieldNames, groupNames).stream().map(r -> r.getStrippedName()).collect(Collectors.toSet());
    }
    /**
     * ako ima grupi, to gi ima vyv fieldNames. Zatova se obikalq po vyrnatite fields proverqva se dali field-a se kazva kato nqkoq grupa i se mahat vsichki fields mejud start-a i kraq na grupata
     * bi imalo problem ako dadena grupa se kazva kato ime na field!!!!
     * @param fieldNames
     * @param groupNames
     * @return
     */
    private Set<FieldOrGroupName> _getFieldNames(String[] fieldNames, String[] groupNames) {
        Set<String> groupNamesSet = Arrays.stream(groupNames).collect(Collectors.toSet());
        Set<FieldOrGroupName> fieldNamesSet = new HashSet<>();
        //if there are some groups, removing all the fields between the group start and the group end. There will be a problem, if there are a fields and a groups with the same names.
        if (fieldNames != null) {
            if (groupNamesSet.size() > 0) {
                List<String> startedGroups = new ArrayList<>();
                for (String fn : fieldNames) {
                    if (startedGroups.size() > 0) {
                        if (fn.equals(startedGroups.get(startedGroups.size() - 1))) {
                            startedGroups.remove(startedGroups.size() - 1);
                        }
                    } else {
                        if (groupNamesSet.contains(fn)) {
                            startedGroups.add(fn);
                        } else {
                            fieldNamesSet.add(createFieldOrGroupName(fn));
                        }
                    }
                }
            } else {
                Arrays.stream(fieldNames).map(this::createFieldOrGroupName).forEach(fieldNamesSet::add);
            }
        }
        return fieldNamesSet;

    }
    /**
     * @param fieldNames -
     * @param groupNames -
     * @param customValues - custom field values if any
     * @param sqlParams - sql params that should be sent to the mailmerge sqls
     * @return the value for each field
     */
    public Map<String, FieldValue> getFieldValues(String[] fieldNames, String[] groupNames, Map<String, Object> customValues, Map<String, Object> sqlParams) {
        Map<FieldOrGroupName, FieldValue> result = new HashMap<>();

        Set<FieldOrGroupName> fieldNamesSet = _getFieldNames(fieldNames, groupNames);
        //removes the parameters that exist in customValues from the fieldNamesList, and sets the customValue to the result
        fieldNamesSet.stream().filter(fn -> customValues.containsKey(fn.getOriginalName())).peek(fn -> log.trace("Replacing field with name {} with the provided custom value", fn)).forEach(fn -> result.put(fn, new FieldValue(ReferenceDataCode.REPORT_FIELD_TYPE_TEXT.code(), customValues.get(fn.getOriginalName()))));
        fieldNamesSet = fieldNamesSet.stream().filter(fn -> !customValues.containsKey(fn.getOriginalName())).collect(Collectors.toSet());

        Map<FieldOrGroupName, CfgReportFieldEntity> documentFields = getFieldConfigs(fieldNamesSet);
        Map<FieldOrGroupName, CfgReportFieldEntity> allFields = addPlaceholderFields(documentFields);//vkliuchva i field-ovete vytre v drug field /ako ima takiva/

        Map<String, List<Map<String, Object>>> executedSqlsBySqlId = executeFieldSqls(allFields, sqlParams);
        Map<FieldOrGroupName, Object> calculatedValues = new HashMap<>();
        for (Map.Entry<FieldOrGroupName, CfgReportFieldEntity> field : documentFields.entrySet()) {
            Object res = getFieldValue(field.getKey(), calculatedValues, allFields, executedSqlsBySqlId);
            result.put(field.getKey(), new FieldValue(field.getValue().getFieldType().getPk().getId(), res));
        }
        return result.entrySet().stream().collect(Collectors.toMap(r -> r.getKey().getOriginalName(), r -> r.getValue()));
    }

    private static Pattern PLACEHOLDER_PATTERN = Pattern.compile("<<(.*?)>>");

    /**
     * ako v daden field ima placeholders, dobavq i tqh v list-a s fields. T.e. ako imame field, chiito SQL e select <<xxx>>, aaaa from bbbb, to rezultata shte sydyrja i xxx i aaaa
     * @param fields
     * @return
     */
    private Map<FieldOrGroupName, CfgReportFieldEntity> addPlaceholderFields(Map<FieldOrGroupName, CfgReportFieldEntity> fields) {
        Map<FieldOrGroupName, CfgReportFieldEntity> result = new HashMap<>();
        result.putAll(fields);
        for (CfgReportFieldEntity e : fields.values()) {
            String sql = e.getSql().getSqlExpression();
            Set<String> placeHolderNames = getPlaceHoldersFromText(sql);
            placeHolderNames.removeIf(p -> result.containsKey(p));
            result.putAll(getFieldConfigs(placeHolderNames.stream().map(this::createFieldOrGroupName).collect(Collectors.toSet())));
        }
        return result;
    }
    private Set<String> getPlaceHoldersFromText(String sql) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(sql);
        Set<String> placeHolderNames = new HashSet<>();
        while (matcher.find()) {
            String placeHolderName = matcher.group(1);
            placeHolderNames.add(placeHolderName);
        }
        return placeHolderNames;
    }

    private Object getFieldValue(FieldOrGroupName fieldId, Map<FieldOrGroupName, Object> calculatedValues, Map<FieldOrGroupName, CfgReportFieldEntity> allFields, Map<String, List<Map<String, Object>>> executedSqlsBySqlId) {
        if (calculatedValues.containsKey(fieldId)) {
            return calculatedValues.get(fieldId);
        }
        CfgReportFieldEntity field = allFields.get(fieldId);
        CfgReportSqlEntity sql = field.getSql();
        //ot SQL-a na field-a, namira rezultata ot izpylnenieto mu v executedSqlsBySqlId, sled tova ot rezultata vadi vsichki koloni s imeto na field-a
        List<Object> fieldValues = executedSqlsBySqlId
                .get(sql.getId())
                .stream()
                .map(r -> {
                    if (!r.containsKey(field.getId())) {
                        throw new RuntimeException("There is no such column " + field.getId() + " in the SQL :" + sql.getId());
                    }
                    return r.get(field.getId());
                })
                .collect(Collectors.toList());
        if (field.getFieldType().getPk().getId().equals(ReferenceDataCode.REPORT_FIELD_TYPE_SPRING_EXPRESSION_LANGUAGE.code())) {
            fieldValues = executeSpringExpressionLanguage(fieldValues);
        }
        //ako sql-a ima mnogo redove, to generira obsht rezultat kato konkatenira vseki edin red + separatorText, za nachalo slaga startText a za kraj - endText
        //ako sql-a ne e konfiguriran da vry6ta mnogo redove, no rezultata sydyrja poveche ot edin red -> hvyrlq se exception.
        Object res;
        String startText = sql.getStartText() == null ? "" : sql.getStartText();
        String endText = sql.getEndText() == null ? "" : sql.getEndText();
        if (sql.getManyRowsFlag() == 1) {
            String separatorText = sql.getSeparatorText() == null ? "" : sql.getSeparatorText();
            String fieldDelimiter = fieldId.getParamValue("delimiter");
            if (fieldDelimiter != null) {
                separatorText = fieldDelimiter;
            }
            res = fieldValues.stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(separatorText));
        } else {
            if (fieldValues.size() > 1) {
                throw new RuntimeException("The sql's manyRowsFlag != 1 and there are more than one records, returned from the sql. Sql's id:" + sql.getId());
            }
            res = fieldValues.size() == 0 ? null : fieldValues.get(0);
        }
        if (res != null && res instanceof String s) {
            res = startText + replacePlaceholders(s, calculatedValues, allFields, executedSqlsBySqlId)+ endText;
        }
        calculatedValues.put(fieldId, res);
        return res;
    }

    private List<Object> executeSpringExpressionLanguage(List<Object> values) {
        List<Object> result = new ArrayList<>();
        for (Object o : values) {
            result.add(executeSpringExpressionLanguage(o));
        }
        return result;
    }






    /**
     * ako v daden field's SQL ima placeholders, to replace-va vseki edin ot tqh s negovoto value!
     * @param result
     * @param allFieldConfigs
     * @param executedSqlsBySqlId
     * @return
     */
    private String replacePlaceholders(String result, Map<FieldOrGroupName, Object> calculatedValues, Map<FieldOrGroupName, CfgReportFieldEntity> allFieldConfigs, Map<String, List<Map<String, Object>>> executedSqlsBySqlId) {
        Set<String> placeholderNames = getPlaceHoldersFromText(result);
        @AllArgsConstructor
        class Res {
            String object;
        }
        Res res = new Res(result);
        placeholderNames.forEach(ph -> res.object = res.object.replaceAll("<<" + ph + ">>", getValueAsString(getFieldValue(createFieldOrGroupName(ph), calculatedValues, allFieldConfigs, executedSqlsBySqlId))));
        return res.object;
    }

    /**
     * vzema unikalnite SQLs za vseki edin field i gi izpylnqva
     * @param fields
     * @param sqlParams
     * @return
     */
    private Map<String, List<Map<String, Object>>> executeFieldSqls(Map<FieldOrGroupName, CfgReportFieldEntity> fields, Map<String, Object> sqlParams) {
        return fields.values()
                .stream()
                .map(r -> r.getSql())
                .collect(Collectors.toMap(r -> r.getId(), r -> r.getSqlExpression(), (r1, r2) -> r1)) //removes the duplicate sqls (multiple fields might be executed from the same sql)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(r -> r.getKey(), r -> sqlRepository.selectRowsAsMap(r.getValue(), sqlParams), (r1, r2) -> r1 ));

    }

    private Map<FieldOrGroupName, CfgReportFieldEntity> getFieldConfigs(Set<FieldOrGroupName> fieldNames) {
        Set<String> strippedNames = fieldNames.stream().map(r -> r.getStrippedName()).collect(Collectors.toSet());
        List<CfgReportFieldEntity> res = CollectionUtils.isEmpty(fieldNames) ? new ArrayList<>() : cfgReportFieldRepository.findAllById(strippedNames);
        if (res.size() != strippedNames.size()) {
            List<String> err = new ArrayList<>();
            err.addAll(strippedNames);
            err.removeAll(res.stream().map(r -> r.getId()).collect(Collectors.toList()));
            throw new RuntimeException("There are no configurations for field(s): " + err.stream().collect(Collectors.joining(", ")));
        }
        Map<String, CfgReportFieldEntity> mapped = res.stream().collect(Collectors.toMap(r -> r.getId(), r -> r));
        return fieldNames.stream().collect(Collectors.toMap(r -> r, r -> mapped.get(r.getStrippedName())));
    }

    @AllArgsConstructor
    @Getter
    public static class FieldValue {
        private String resultType;
        private Object object;
        public boolean isHtml() {
            return checkResultType(ReferenceDataCode.REPORT_FIELD_TYPE_HTML.code());
        }
        public boolean isSpEl() {
            return checkResultType(ReferenceDataCode.REPORT_FIELD_TYPE_SPRING_EXPRESSION_LANGUAGE.code());
        }

        public boolean checkResultType(String type) {
            return resultType.equals(type);
        }
    }

    protected String getValueAsString(Object o) {
        if (o == null) {
            return "";
        } else if (o instanceof Date d) {
            return DateUtils.formatDate(d);
        } else {
            return o.toString();
        }
    }
}
