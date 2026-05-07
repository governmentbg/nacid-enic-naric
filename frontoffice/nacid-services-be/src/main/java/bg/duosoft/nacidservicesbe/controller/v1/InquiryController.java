package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.InquiryService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.inquiry.InquiryApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.inquiry.InquiryDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.inquiry.InquiryFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 14:13
 */
@Api(tags = Tags.INQUIRY)
@RestController
@RequestMapping("/api/v1/inquiry")
@RequiredArgsConstructor
public class InquiryController extends BaseApplicationController<InquiryApplicationDTO, CommonApplicantDetailsDTO, InquiryDetailsDTO>{

    private final InquiryService inquiryService;
    private final InquiryApplicantDetailsValidator inquiryApplicantDetailsValidator;
    private final InquiryDetailsValidator inquiryDetailsValidator;
    private final InquiryFilingValidator inquiryFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return inquiryService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return inquiryApplicantDetailsValidator;
    }

    @Override
    public Validator<InquiryDetailsDTO> getApplicationSpecificDetailsValidator() {
        return inquiryDetailsValidator;
    }

    @Override
    public Validator<InquiryApplicationDTO> getFilingValidator() {
        return inquiryFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }
}
