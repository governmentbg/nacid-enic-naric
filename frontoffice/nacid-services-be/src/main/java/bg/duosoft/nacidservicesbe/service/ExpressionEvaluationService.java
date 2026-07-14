package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 17:16
 */
public interface ExpressionEvaluationService {

    boolean isExpressionValidForApplication(CommonApplicationDTO application, String expression);
}
