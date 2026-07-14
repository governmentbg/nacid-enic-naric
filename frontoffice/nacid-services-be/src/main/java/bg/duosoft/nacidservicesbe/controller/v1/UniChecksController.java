package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CalculatedFeesDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.controller.utils.EducationDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.UniChecksService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.unichecks.UniChecksApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.unichecks.UniChecksEducationDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.unichecks.UniChecksFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 15:35
 */
@Api(tags = Tags.UNI_CHECKS)
@RestController
@RequestMapping("/api/v1/uni-checks")
@RequiredArgsConstructor
public class UniChecksController extends BaseApplicationController<UniChecksApplicationDTO, CommonApplicantDetailsDTO, UniChecksEducationDetailsDTO> {

    private final UniChecksService uniChecksService;
    private final UniChecksApplicantDetailsValidator uniChecksApplicantDetailsValidator;
    private final UniChecksEducationDetailsValidator uniChecksEducationDetailsValidator;
    private final UniChecksFilingValidator uniChecksFilingValidator;
    private final PaymentService paymentService;

    @GetMapping("/calculate-uni-checks-fees")
    @PreAuthorize("isAuthenticated()")
    public CalculatedFeesDTO calculateUniChecksFees(@RequestParam(required = false, defaultValue = "false") Boolean statute,
                                                    @RequestParam(required = false, defaultValue = "false") Boolean authenticity,
                                                    @RequestParam(required = false, defaultValue = "false") Boolean recommendation,
                                                    @RequestParam(required = false) String serviceType,
                                                    @RequestParam ApplicantType applicantType) {
        if((!statute && !authenticity && !recommendation) || !StringUtils.hasText(serviceType)){
            return new CalculatedFeesDTO();
        }
        Map<String, String> params = uniChecksService.createUniChecksFeeCalculationParamsMap(statute, authenticity, recommendation, serviceType, applicantType);
        return paymentService.getCalculatedFees(params, uniChecksService.getPaymentModule());
    }

    @Override
    public BaseApplicationService getApplicationService() {
        return uniChecksService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return uniChecksApplicantDetailsValidator;
    }

    @Override
    public Validator<UniChecksEducationDetailsDTO> getApplicationSpecificDetailsValidator() {
        return uniChecksEducationDetailsValidator;
    }

    @Override
    public Validator<UniChecksApplicationDTO> getFilingValidator() {
        return uniChecksFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }

    @Override
    public void preSaveRequestSpecificDetails(UniChecksEducationDetailsDTO specificDetails) {
        preSaveUniChecksEducationDetails(specificDetails);
    }

    private void preSaveUniChecksEducationDetails(UniChecksEducationDetailsDTO educationDetails){
        EducationDetailsDTOUtils.preSaveEducationDetails(educationDetails);
        EducationDetailsDTOUtils.preSaveEducationDetailsSpecialities(educationDetails);
        if(educationDetails.getDiplomaHolder() != null){
            ApplicantDetailsDTOUtils.preSaveNaturalPerson(educationDetails.getDiplomaHolder(), getBoPublicServicesService());
        }
        if(educationDetails.getServiceType() != null && !StringUtils.hasText(educationDetails.getServiceType().getId())){
            educationDetails.setServiceType(null);
        }
    }
}
