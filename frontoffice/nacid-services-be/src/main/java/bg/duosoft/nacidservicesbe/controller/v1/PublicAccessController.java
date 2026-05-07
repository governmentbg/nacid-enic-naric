package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.PublicAccessService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.publicaccess.PublicAccessApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.publicaccess.PublicAccessDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.publicaccess.PublicAccessFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 15:18
 */
@Api(tags = Tags.PUBLIC_ACCESS)
@RestController
@RequestMapping("/api/v1/public-access")
@RequiredArgsConstructor
public class PublicAccessController extends BaseApplicationController<PublicAccessApplicationDTO, CommonApplicantDetailsDTO, PublicAccessDetailsDTO>{

    private final PublicAccessService publicAccessService;
    private final PublicAccessApplicantDetailsValidator publicAccessApplicantDetailsValidator;
    private final PublicAccessDetailsValidator publicAccessDetailsValidator;
    private final PublicAccessFilingValidator publicAccessFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return publicAccessService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return publicAccessApplicantDetailsValidator;
    }

    @Override
    public Validator<PublicAccessDetailsDTO> getApplicationSpecificDetailsValidator() {
        return publicAccessDetailsValidator;
    }

    @Override
    public Validator<PublicAccessApplicationDTO> getFilingValidator() {
        return publicAccessFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }
}
