package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CalculatedFeesDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.OfficialNotesService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.officialnotes.OfficialNotesApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.officialnotes.OfficialNotesDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.officialnotes.OfficialNotesFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 14:13
 */
@Api(tags = Tags.OFFICIAL_NOTES)
@RestController
@RequestMapping("/api/v1/official-notes")
@RequiredArgsConstructor
public class OfficialNotesController extends BaseApplicationController<OfficialNotesApplicationDTO, CommonApplicantDetailsDTO, OfficialNotesDetailsDTO> {

    private final OfficialNotesService officialNotesService;
    private final OfficialNotesApplicantDetailsValidator officialNotesApplicantDetailsValidator;
    private final OfficialNotesDetailsValidator officialNotesDetailsValidator;
    private final OfficialNotesFilingValidator officialNotesFilingValidator;
    private final PaymentService paymentService;

    @GetMapping("/calculate-official-notes-fees")
    @PreAuthorize("isAuthenticated()")
    public CalculatedFeesDTO calculateOfficialNotesFees(@RequestParam(required = false) String serviceType, @RequestParam String kinds) {
        if (!StringUtils.hasText(kinds) && !StringUtils.hasText(serviceType)) {
            return new CalculatedFeesDTO();
        }
        String[] kindsArr = kinds.split(";");
        List<OfficialNoteKind> kindsList = Arrays.stream(kindsArr).map(str -> OfficialNoteKind.valueOf(str)).collect(Collectors.toList());
        Map<String, String> params = officialNotesService.createOfficialNotesFeeCalculationParamsMap(serviceType, kindsList);
        return paymentService.getCalculatedFees(params, officialNotesService.getPaymentModule());
    }

    @Override
    public BaseApplicationService getApplicationService() {
        return officialNotesService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return officialNotesApplicantDetailsValidator;
    }

    @Override
    public Validator<OfficialNotesDetailsDTO> getApplicationSpecificDetailsValidator() {
        return officialNotesDetailsValidator;
    }

    @Override
    public Validator<OfficialNotesApplicationDTO> getFilingValidator() {
        return officialNotesFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }
}
