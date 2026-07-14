package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 20:30
 */
@Slf4j
@Service

public class GroupSqlExecutor extends SqlExecutorBase {
    public Set<String> getGroupNames(String[] fieldNames, String[] groupNames) {
        return _getGroupNames(fieldNames, groupNames).stream().map(r -> r.getOriginalName()).collect(Collectors.toSet());
    }

    private Set<FieldOrGroupName> _getGroupNames(String[] fieldNames, String[] groupNames) {
        return groupNames == null || groupNames.length == 0 ? new HashSet<>() : Arrays.stream(groupNames).map(r -> createFieldOrGroupName(r)).collect(Collectors.toSet());
    }

    @AllArgsConstructor
    @Getter
    private static class GroupValue {
        CfgReportSqlEntity groupDetails;
        List<Map<String, Object>> groupValue;
    }

    @AllArgsConstructor
    private static class GroupConfig {
        FieldOrGroupName groupName;
        CfgReportSqlEntity sql;
    }

    public Map<String, List<Map<String, Object>>> getGroupValues(String[] fieldNames, String[] groupNames, Map<String, Object> customValues, Map<String, Object> params) {
        Set<FieldOrGroupName> groupNamesSet = _getGroupNames(fieldNames, groupNames);
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        groupNamesSet
                .stream()
                .filter(gn -> customValues != null && customValues.containsKey(gn.getOriginalName()))
                .peek(gn -> log.trace("Replacing group name {} with the provided custom value", gn))
                .forEach(gn -> result.put(gn.getOriginalName(), (List<Map<String, Object>>) customValues.get(gn)));
        groupNamesSet = groupNamesSet
                .stream()
                .filter(gn -> customValues == null || !customValues.containsKey(gn.getOriginalName()))
                .collect(Collectors.toSet());

        Map<String, GroupConfig> groups = getGroupConfigs(groupNamesSet);
        return groups
                .entrySet()
                .stream()
                .collect(Collectors.toMap(r -> r.getKey(), r -> _createGroupValue(r.getValue(), params).getGroupValue()));
    }

    private GroupValue _createGroupValue(GroupConfig gc, Map<String, Object> params) {
        Map<String, Object> sqlParams = new HashMap<>();
        sqlParams.putAll(params);
        if (!ObjectUtils.isEmpty(gc.groupName.getParams())) {
            sqlParams.putAll(gc.groupName.getParams());
        }
        List<Map<String, Object>> res = sqlRepository.selectRowsAsMap(gc.sql.getSqlExpression(), sqlParams);
        CfgReportSqlEntity sql = gc.sql;


        //ako ima SpEL fields, im izchislqva stojnostite, kato izpylnqva SpEL-a, ako ima HTML fields, hvyrlq exception - tozi tip ne e support-van v grupi
        /**
         * fields by type
         *  - key -> keyType
         *  - value
         *      - key - fieldName
         *      - value - fieldEntity
         */

        Map<String, Map<String, CfgReportFieldEntity>> fieldsByType = sql
                .getFields()
                .stream()
                .collect(Collectors.groupingBy(r -> r.getFieldType().getPk().getId(), Collectors.toMap(r -> r.getId(), r -> r)));
        if (fieldsByType.containsKey(ReferenceDataCode.REPORT_FIELD_TYPE_HTML.code())) {
            throw new RuntimeException("HTML field type not supported!!!");
        }
        if (fieldsByType.containsKey(ReferenceDataCode.REPORT_FIELD_TYPE_SPRING_EXPRESSION_LANGUAGE.code())) {
            Map<String, CfgReportFieldEntity> spELs = fieldsByType.get(ReferenceDataCode.REPORT_FIELD_TYPE_SPRING_EXPRESSION_LANGUAGE.code());
            for (Map<String, Object> row : res) {
                spELs.entrySet().forEach(e -> row.put(e.getKey(), executeSpringExpressionLanguage(row.get(e.getKey()))));
            }
        }
        return new GroupValue(gc.sql, res);
    }

    /**
     *
     * @param groupNamesList
     * @return
     *  - key - original group name
     */
    private Map<String, GroupConfig> getGroupConfigs(Set<FieldOrGroupName> groupNamesList) {
        Set<String> strippedNames = groupNamesList.stream().map(r -> r.getStrippedName()).collect(Collectors.toSet());
        Map<String, CfgReportSqlEntity> res = groupNamesList.size() == 0 ? new HashMap<>() : cfgReportSqlRepository.findAllByIdInAndGroupFlag(strippedNames, 1).stream().collect(Collectors.toMap(r -> r.getId(), Function.identity()));
        if (res.size() != strippedNames.size()) {
            List<String> err = new ArrayList<>();
            err.addAll(strippedNames);
            err.removeAll(res.keySet());
            throw new RuntimeException("There are no configurations for group(s): " + err.stream().collect(Collectors.joining(", ")));
        }
        return groupNamesList.stream().collect(Collectors.toMap(r -> r.getOriginalName(), r -> new GroupConfig(r, res.get(r.getStrippedName()))));
    }

}
