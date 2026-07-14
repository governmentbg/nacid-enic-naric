package bg.duosoft.nacidservicesbe.evaluations.utils;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeRequirementDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 15:20
 */
public class CommonApplicationEvaluationsUtils {

    public static void evaluateDeclarations(CommonApplicationDTO form, List<EvaluationDTO> evaluations){
        boolean agreeDataUsage = false;
        boolean documentsDeclaration = false;
        if(form.getApplicantDetails() != null){
            agreeDataUsage = Boolean.TRUE.equals(form.getApplicantDetails().getAgreeDataUsage());
            documentsDeclaration = Boolean.TRUE.equals(form.getApplicantDetails().getAgreeDataUsage());
        }
        evaluations.add(new EvaluationDTO("rule.applicantDetails.agreeDataUsage", agreeDataUsage));
        evaluations.add(new EvaluationDTO("rule.applicantDetails.documentsDeclaration", documentsDeclaration));

    }

    public static void evaluateResultReceive(CommonApplicationDTO form, List<EvaluationDTO> evaluations){
        boolean resultReceive = false;
        if(form.getApplicantDetails() != null && form.getApplicantDetails().getCertificateReceiveForms() == null
                && form.getApplicantDetails().getResultReceive() != null
                && form.getApplicantDetails().getResultReceive().getResultReceive() != null
                && StringUtils.hasText(form.getApplicantDetails().getResultReceive().getResultReceive().getId())){
            resultReceive = true;
        }
        if(form.getApplicantDetails() != null && form.getApplicantDetails().getCertificateReceiveForms() != null && !form.getApplicantDetails().getCertificateReceiveForms().isEmpty()
                && (
                        (
                        form.getApplicantDetails().getResultReceiveElectronic() != null
                        && form.getApplicantDetails().getResultReceiveElectronic().getResultReceive() != null
                        && StringUtils.hasText(form.getApplicantDetails().getResultReceiveElectronic().getResultReceive().getId())
                        )
                    ||
                        (
                        form.getApplicantDetails().getResultReceivePaper() != null
                        && form.getApplicantDetails().getResultReceivePaper().getResultReceive() != null
                        && StringUtils.hasText(form.getApplicantDetails().getResultReceivePaper().getResultReceive().getId())
                        )
                    )
        ){
            resultReceive = true;
        }

        evaluations.add(new EvaluationDTO("rule.applicantDetails.resultReceive", resultReceive));
    }

    public static void evaluateCertificateReceiveForm(CommonApplicationDTO form, List<EvaluationDTO> evaluations){
        boolean certReceive = false;
        if(form.getApplicantDetails() != null && form.getApplicantDetails().getCertificateReceiveForms() != null && !form.getApplicantDetails().getCertificateReceiveForms().isEmpty()){
            certReceive = true;
        }
        evaluations.add(new EvaluationDTO("rule.applicantDetails.certificateResultForm", certReceive));
    }

    public static void evaluateAttachedDocuments(CommonApplicationDTO form, List<EvaluationDTO> evaluations, List<CfgDocTypeRequirementDTO> requirements) {
        Map<String, EvaluationDTO> evalMap = new LinkedHashMap<>();
        requirements.forEach(r -> evalMap.put(r.getRequirementKey(), new EvaluationDTO(r.getRequirementKey(), false, r.getTemplateUrl())));

        if(form.getDocumentDetails() != null && !form.getDocumentDetails().getAttachments().isEmpty()){
            requirements.forEach(r -> {
                long attOk = form.getDocumentDetails().getAttachments().stream().
                        filter(
                                att -> att.getAttachmentType() != null && att.getAttachmentType().getId().equals(r.getDocType().getId()) &&
                                        (r.getCopyTypeCode() == null || att.getAttachmentForm() != null && att.getAttachmentForm().getId() != null && att.getAttachmentForm().getId().equals(r.getCopyTypeCode()))
                        ).count();
                evalMap.get(r.getRequirementKey()).setEvaluationValue(attOk > 0);
            });
        }

        evalMap.keySet().forEach(key -> evaluations.add(evalMap.get(key)));
    }
}
