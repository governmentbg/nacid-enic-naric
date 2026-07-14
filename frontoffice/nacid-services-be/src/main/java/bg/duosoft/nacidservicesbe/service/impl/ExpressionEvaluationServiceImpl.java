package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidservicesbe.service.ExpressionEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 17:16
 */
@Service
@RequiredArgsConstructor
public class ExpressionEvaluationServiceImpl implements ExpressionEvaluationService {

    private final BeanFactory beanFactory;

    @Override
    public boolean isExpressionValidForApplication(CommonApplicationDTO application, String expression) {
        if(!StringUtils.hasText(expression)){
            return true;
        }
        StandardEvaluationContext context = new StandardEvaluationContext(application);

        context.setBeanResolver(new BeanFactoryResolver(beanFactory));
        Expression expr = new SpelExpressionParser().parseExpression(expression);
        return (boolean) expr.getValue(context);
    }
}
