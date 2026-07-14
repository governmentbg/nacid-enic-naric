package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithServiceType;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 18:17
 */
public interface BaseApplicationService<A extends CommonApplicationDTO, AD extends CommonApplicantDetailsDTO, SD> {

    A getApplication(Integer id);
    void deleteApplication(Integer id);
    A getApplicationForCheckup(String dossierNumber, String accessCode);
    AD saveApplicantDetails(Integer applicationId, AD applicantDetails);
    SD saveRequestSpecificDetails(Integer applicationId, SD specificDetails);
    DocumentDetailsDTO saveDocumentDetails(Integer applicationId, DocumentDetailsDTO documentDetails);
    List<String> finalizeApplication(Integer id);
    List<String> fileApplication(Integer id);
    List<String> fileApplicationInternal(Integer id);
    String fileSignedApplication(Integer id, SignedApplicationDocumentDTO signedApplication);
    String fileSignedApplicationInternal(Integer id, SignedApplicationDocumentDTO signedApplication);
    List<EvaluationDTO> evaluateApplication(Integer id);
    String getApplicationReceiptTemplateName();
    byte[] changeApplicationToAccepted(AcceptApplicationRequestDTO acceptApplicationRequestDTO);
    void revertApplicationToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest);
    boolean applicationIsReversibleToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest);
    CalculatedFeesDTO calculateFees(Integer id);
    Map<String, String> createFeeCalculationParamsMap(A application);
    String getPaymentModule();
    byte[] regenerateReceipt(Integer id, boolean addToApplication, boolean keepOldReceipt, FoApplicationStatus status);
    ReferenceDataDTO saveServiceType(Integer applicationId, WithServiceType withServiceType);
    void changeFoApplicationStatus(ChangeFoApplicationStatusRequestDTO changeStatusRequest);
    String getPayerName(A app);
}
