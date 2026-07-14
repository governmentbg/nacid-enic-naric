package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.03.2023
 * Time: 12:01
 */
public interface BaseApplicationEvaluations<A extends CommonApplicationDTO> {

    List<EvaluationDTO> evaluateApplication(A application);
}
