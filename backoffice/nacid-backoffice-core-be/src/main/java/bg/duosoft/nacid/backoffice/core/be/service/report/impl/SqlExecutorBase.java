package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.SqlRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportSqlRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 20:31
 */
public class SqlExecutorBase {
    @Autowired
    protected CfgReportFieldRepository cfgReportFieldRepository;
    @Autowired
    protected CfgReportSqlRepository cfgReportSqlRepository;
    @Autowired
    protected SqlRepository sqlRepository;
    @Autowired
    private BeanFactory beanFactory;

    private String stripFieldName(String fieldName) {
        return fieldName.split(" ")[0];
    }
    private Map<String, String> getFieldNameParameters(String fieldName) {
        String[] parts = fieldName.split(" ", 2);
        if (parts.length == 2) {
            String[] params = parts[1].split(";");
            return Arrays.stream(params).collect(Collectors.toMap(r -> r.split("=")[0].trim(), this::generateParameterValue));
        }
        return null;
    }
    private String generateParameterValue(String param) {
        String val = param.split("=")[1];
        val = val.trim();



        //replace na unicode chars
        StringBuffer buf = new StringBuffer();
        Matcher m = Pattern.compile("\\\\u([0-9A-Fa-f]{4})").matcher(val);
        while (m.find()) {
            try {
                int cp = Integer.parseInt(m.group(1), 16);
                m.appendReplacement(buf, "");
                buf.appendCodePoint(cp);
            } catch (NumberFormatException e) {
            }
        }
        m.appendTail(buf);
        return buf.toString();
    }

    protected FieldOrGroupName createFieldOrGroupName(String name) {
        return new FieldOrGroupName(name, stripFieldName(name), getFieldNameParameters(name));
    }

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    protected class FieldOrGroupName {
        private String originalName;
        private String strippedName;
        private Map<String, String> params;
        public String getParamValue(String paramName) {
            return params == null ? null : params.get(paramName);
        }
    }

    protected Object executeSpringExpressionLanguage(Object o) {
        String val = o == null ? null : o.toString();
        if (val != null) {
            StandardEvaluationContext context = new StandardEvaluationContext();

            context.setBeanResolver(new BeanFactoryResolver(beanFactory));
            Expression expr = new SpelExpressionParser().parseExpression(val);
            return expr.getValue(context);
        } else {
            return null;
        }


    }
}
