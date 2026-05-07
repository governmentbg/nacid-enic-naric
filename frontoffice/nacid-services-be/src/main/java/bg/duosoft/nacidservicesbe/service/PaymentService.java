package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacid.payments.dto.payments.LiabilityDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CalculatedFeesDTO;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.04.2023
 * Time: 18:57
 */
public interface PaymentService {

    String SERVICE_TYPE_PARAM = "SERVICE_TYPE";
    String APPLICATION_SUBTYPE_PARAM = "APPLICATION_SUBTYPE";
    String AUTHENTICITY_FLAG_PARAM = "AUTHENTICITY_FLAG";
    String RECOMMENDATION_FLAG_PARAM = "RECOMMENDATION_FLAG";
    String STATUTE_FLAG_PARAM = "STATUTE_FLAG";
    String LEGAL_NATURE_TYPE_PARAM = "LEGAL_NATURE_TYPE";
    String LEGAL_TYPE_PARAM = "LEGAL_TYPE";
    String DISSERTATION_NOTE_FLAG_PARAM = "DISSERTATION_NOTE_FLAG";
    String PAPER_NOTE_FLAG_PARAM = "PAPER_NOTE_FLAG";
    String POSITION_NOTE_FLAG_PARAM = "POSITION_NOTE_FLAG";
    String PROJECT_NOTE_FLAG_PARAM = "PROJECT_NOTE_FLAG";

    String PAYMENT_MODULE_REGPROF = "regprof";
    String PAYMENT_MODULE_RUDI = "rudi";
    String PAYMENT_MODULE_LIBRARY = "library";

    CalculatedFeesDTO getCalculatedFees(Map<String, String> params, String module);
    CalculatedFeesDTO insertFeesForPayment(Map<String, String> params, String module, String tempNumber, String user, String description, String applicantName);
    void deleteFeesFromPayments(String tempNumber);
    LiabilityDTO insertLiability(LiabilityDTO liability);
    LiabilityDTO getLiability(String tempNumber);
}
