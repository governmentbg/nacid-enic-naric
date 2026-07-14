package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CPaymentsErrorAdminEmailData;
import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import bg.duosoft.nacid.payments.client.client.feecalculation.AdminFeeCalculationClient;
import bg.duosoft.nacid.payments.client.client.feeinsertion.AdminFeeInsertionClient;
import bg.duosoft.nacid.payments.client.client.liabilities.AdminLiabilitiesClient;
import bg.duosoft.nacid.payments.dto.payments.*;
import bg.duosoft.nacidfrontofficedto.services.common.application.CalculatedFeesDTO;
import bg.duosoft.nacidservicesbe.mapper.fee.FeeCalculationResponseMapper;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.04.2023
 * Time: 17:50
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final AdminFeeCalculationClient adminFeeCalculationClient;
    private final AdminFeeInsertionClient adminFeeInsertionClient;
    private final AdminLiabilitiesClient adminLiabilitiesClient;
    private final FeeCalculationResponseMapper feeCalculationResponseMapper;
    private final MailSenderService mailSenderService;

    @Override
    public CalculatedFeesDTO getCalculatedFees(Map<String, String> params, String module) {
        FeeCalculationRequest feeCalcRequest = createFeeCalculationRequest(params, module);
        return getCalculatedFees(feeCalcRequest);
    }

    @Override
    public CalculatedFeesDTO insertFeesForPayment(Map<String, String> params, String module, String tempNumber, String user, String description, String applicantName) {
        FeeCalculationRequest feeCalcRequest = createFeeCalculationRequest(params, module);
        FeeInsertionRequest insertionRequest = new FeeInsertionRequest(feeCalcRequest, tempNumber, null, user, description, applicantName);
        try {
            FeeInsertionResponse insertionResponse = adminFeeInsertionClient.insert(insertionRequest);
            return feeCalculationResponseMapper.toDto(insertionResponse.getCalculationResponse());
        } catch (ValidationErrorException ve){
            if(ve.getErrors().size() == 1 && ve.getErrors().stream().filter(err -> err.getPointer().equals("paid-liability-exists")).count() == 1){
                //Just return the newly calculated fees, it's NACID's responsibility to do something to fix the fees
                return getCalculatedFees(feeCalcRequest);
            } else {
              throw new RuntimeException("Fees insertion into payments failed: "+ve.getErrors().stream().map(err -> err.getPointer()).reduce((e1, e2) -> e1+"; "+e2).get());
            }
        }
    }

    @Override
    public void deleteFeesFromPayments(String tempNumber) {
        try {
            adminLiabilitiesClient.deleteByFoNumber(tempNumber);
        } catch (Exception e){
            mailSenderService.sendPaymentsErrorAdminMail(CPaymentsErrorAdminEmailData.builder()
                            .errorMessage("Failed to remove liabilities for temp number that will be reverted: "+tempNumber)
                            .stackTrace(ExceptionUtils.getStackTrace(e))
                    .build());
            log.error("Failed to remove liabilities for temp number that will be reverted: "+tempNumber, e);
        }
    }

    @Override
    public LiabilityDTO insertLiability(LiabilityDTO liability) {
        LiabilityDTO saved = adminLiabilitiesClient.create(liability);
        return saved;
    }

    @Override
    public LiabilityDTO getLiability(String tempNumber) {
        return adminLiabilitiesClient.getByFrontOfficeReferenceNumber(tempNumber);
    }

    private CalculatedFeesDTO getCalculatedFees(FeeCalculationRequest feeCalcRequest) {
        FeeCalculationResponse response = adminFeeCalculationClient.calculate(feeCalcRequest);
        return feeCalculationResponseMapper.toDto(response);
    }

    private FeeCalculationRequest createFeeCalculationRequest(Map<String, String> params, String module){
        FeeCalculationRequest feeCalcRequest = new FeeCalculationRequest();
        feeCalcRequest.setModule(module);
        List<FeeCalculationRequest.FeeCalculationParam> feeCalcParams = new ArrayList<>();
        params.keySet().stream().forEach(key -> {
            feeCalcParams.add(new FeeCalculationRequest.FeeCalculationParam(key, params.get(key)));
        });
        feeCalcRequest.setParams(feeCalcParams);
        return feeCalcRequest;
    }

}
