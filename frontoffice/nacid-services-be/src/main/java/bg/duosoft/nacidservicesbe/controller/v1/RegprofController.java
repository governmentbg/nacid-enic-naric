package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.CalculatedFeesDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationEntryDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.controller.utils.EducationDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.RegprofService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.regprof.RegprofApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.regprof.RegprofEducationDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.regprof.RegprofFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.12.2022
 * Time: 16:06
 */
@RestController
@Api(tags = Tags.REGPROF)
@RequestMapping("/api/v1/regprof")
@RequiredArgsConstructor
public class RegprofController extends BaseApplicationController<RegprofApplicationDTO, RegprofApplicantDetailsDTO, RegprofEducationDetailsDTO> {

    private final RegprofService regprofService;
    private final RegprofApplicantDetailsValidator regprofApplicantDetailsValidator;
    private final RegprofEducationDetailsValidator regprofEducationDetailsValidator;
    private final RegprofFilingValidator regprofFilingValidator;
    private final PaymentService paymentService;

    @GetMapping("/calculate-regprof-fees")
    @PreAuthorize("isAuthenticated()")
    public CalculatedFeesDTO calculateRegprofFees(@RequestParam(required = false) String serviceType) {
        if(!StringUtils.hasText(serviceType)){
            return new CalculatedFeesDTO();
        }
        Map<String, String> params = regprofService.createRegprofFeeCalculationParamsMap(serviceType);
        return paymentService.getCalculatedFees(params, regprofService.getPaymentModule());
    }

    @Override
    public BaseApplicationService getApplicationService() {
        return regprofService;
    }

    @Override
    public Validator<RegprofApplicantDetailsDTO> getApplicantDetailsValidator() {
        return regprofApplicantDetailsValidator;
    }

    @Override
    public Validator<RegprofEducationDetailsDTO> getApplicationSpecificDetailsValidator() {
        return regprofEducationDetailsValidator;
    }

    @Override
    public Validator<RegprofApplicationDTO> getFilingValidator() {
        return regprofFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(RegprofApplicantDetailsDTO applicantDetails) {
       preSaveRegprofApplicantDetails(applicantDetails, getBoPublicServicesService());
    }

    public static void preSaveRegprofApplicantDetails(RegprofApplicantDetailsDTO applicantDetails, BoPublicServicesService boPublicServicesService){
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, boPublicServicesService);
        if(!applicantDetails.isQualificationNamesDifferent()){
            applicantDetails.setQualificationNames(null);
        }
        ApplicantDetailsDTOUtils.preSaveWithIdentifier(applicantDetails.getQualificationNames(), boPublicServicesService);
    }

    @Override
    public void preSaveRequestSpecificDetails(RegprofEducationDetailsDTO specificDetails) {
        super.preSaveRequestSpecificDetails(specificDetails);
        preSaveRegprofEducationDetails(specificDetails);
    }

    public static void preSaveRegprofEducationDetails(RegprofEducationDetailsDTO educationDetails){
        if(!educationDetails.isEducationSelected()){
            educationDetails.setEducation(null);
        } else {
            if(educationDetails.getEducation() != null){
                if(educationDetails.getEducation().getKind().equals(EducationType.AFTER_DIPLOMA_QUALIFICATION)){
                    educationDetails.getEducation().setEducationEntrySecondary(null);
                } else if(educationDetails.getEducation().getKind().equals(EducationType.HIGHER_EDUCATION)){
                    educationDetails.getEducation().setEducationEntryADQ(null);
                    educationDetails.getEducation().setEducationEntrySecondary(null);
                } else {
                    educationDetails.getEducation().setEducationEntryADQ(null);
                    educationDetails.getEducation().setEducationEntryHigher(null);
                }

                preSaveEducationEntry(educationDetails.getEducation().getEducationEntryHigher());
                preSaveEducationEntry(educationDetails.getEducation().getEducationEntrySecondary());
                preSaveEducationEntry(educationDetails.getEducation().getEducationEntryADQ());
            }
        }
        if(!educationDetails.isExperienceSelected()){
            educationDetails.setExperience(null);
        } else {
            if(educationDetails.getExperience().getExperienceDocuments() != null){
                educationDetails.getExperience().getExperienceDocuments().stream().forEach(expDoc -> {
                    if(expDoc.getWorkPeriods() != null) {
                        expDoc.getWorkPeriods().stream().forEach(workPeriod -> {
                            if(workPeriod.getWorkDayHours() != null && !StringUtils.hasText(workPeriod.getWorkDayHours().getId())){
                                workPeriod.setWorkDayHours(null);
                            }
                        });
                    }
                    if(expDoc.getType() != null && !StringUtils.hasText(expDoc.getType().getId())){
                        expDoc.setType(null);
                    }
                });
            }
        }
        if(educationDetails.getServiceType() != null && !StringUtils.hasText(educationDetails.getServiceType().getId())){
            educationDetails.setServiceType(null);
        }
    }

    public static void preSaveEducationEntry(RegprofEducationEntryDTO entry){
        if(entry != null){
            EducationDetailsDTOUtils.preSaveEducationDetailsSpecialities(entry);
            if(entry.getDocumentKind() != null && entry.getDocumentKind().getId() == null){
                entry.setDocumentKind(null);
            }
            if(entry.getEduLevel() != null && !StringUtils.hasText(entry.getEduLevel().getId())){
                entry.setEduLevel(null);
            }
            if(entry.getQualificationRank() != null && !StringUtils.hasText(entry.getQualificationRank().getId())){
                entry.setQualificationRank(null);
            }
        }
    }
}
