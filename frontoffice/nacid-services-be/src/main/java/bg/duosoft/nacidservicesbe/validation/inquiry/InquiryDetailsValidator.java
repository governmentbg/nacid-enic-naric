package bg.duosoft.nacidservicesbe.validation.inquiry;

import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryKind;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesService;
import bg.duosoft.nacidservicesbe.service.InquiryService;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:36
 */
@Component
@RequiredArgsConstructor
public class InquiryDetailsValidator implements Validator<InquiryDetailsDTO> {

    private final InquiryService inquiryService;
    private final BoPublicServicesService boPublicServicesService;

    @Override
    public List<ValidationError> validate(InquiryDetailsDTO details, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmptyCollection(errors, details.getInquiryKinds(), "inquiryKinds", ValidationMessageCodes.SELECT_CODE);
        rejectIfEmptyString(errors, details.getPeriodFrom(), "periodFrom", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, details.getPeriodTo(), "periodTo", ValidationMessageCodes.REQUIRED_CODE);

        if(StringUtils.hasText(details.getPeriodFrom()) && StringUtils.hasText(details.getPeriodTo())){
            try {
                if(Integer.parseInt(details.getPeriodFrom()) > Integer.parseInt(details.getPeriodTo())){
                    reject(errors, "periodFrom", ValidationMessageCodes.START_YEAR_BIG_CODE);
                }
            } catch (Exception e){}
        }

        rejectIfNotMatchRegex(errors, details.getPeriodFrom(), "\\d{4}", "periodFrom", ValidationMessageCodes.INVALID_CODE);
        rejectIfNotMatchRegex(errors, details.getPeriodTo(), "\\d{4}", "periodTo", ValidationMessageCodes.INVALID_CODE);

        rejectIfNotMatchRegex(errors, details.getPreviousInquiryNum(), "94\\-05\\-\\d{1,3}/\\d{2}\\.\\d{2}\\.\\d{4}", "previousInquiryNum", ValidationMessageCodes.PREVIOUS_INQ_INVALID_CODE);

        if(details.getInquiryKinds() != null && details.getInquiryKinds().size()>0){
            boolean hasImpCitations = details.getInquiryKinds().stream().anyMatch(kd -> kd.equals(InquiryKind.IMPACT_FACTOR_CITINGS));
            boolean hasCitations = details.getInquiryKinds().stream().anyMatch(kd -> kd.equals(InquiryKind.CITINGS));

            if(hasImpCitations && !hasCitations){
                rejectIfEmptyString(errors, details.getPreviousInquiryNum(), "previousInquiryNum", ValidationMessageCodes.REQUIRED_PREVIOUS_INQ_OR_CITATIONS_CODE);
            }
        }

        if(errors.stream().filter(err -> err.getPointer().equals("previousInquiryNum")).count() == 0 && StringUtils.hasText(details.getPreviousInquiryNum())){
            String[] entryDetails = details.getPreviousInquiryNum().split("/");
            Boolean previousNumIsOk = boPublicServicesService.applicationNotDeniedByEntryDetails(entryDetails[0], LocalDate.parse(entryDetails[1], DateTimeFormatter.ofPattern(DTOConstants.DATE_FORMAT)));
            if(previousNumIsOk == null || !previousNumIsOk){
                reject(errors, "previousInquiryNum",  ValidationMessageCodes.PREVIOUS_INQ_ERROR_OR_BAD_CODE);
            }
        }

        if(errors.size() == 0){
            Integer id = (Integer)args[0];
            InquiryApplicationDTO application = inquiryService.getApplication(id);
            if(application.getSubmittedOrFinalized() && application.getInquiryDetails() != null && application.getInquiryDetails().getInquiryKinds() != null){
                List<InquiryKind> dbKinds = application.getInquiryDetails().getInquiryKinds();
                if(!dbKinds.containsAll(details.getInquiryKinds())){
                    reject(errors, "inquiryKinds", ValidationMessageCodes.INVALID_CODE);
                } else if(!details.getInquiryKinds().containsAll(dbKinds)){
                    reject(errors, "inquiryKinds", ValidationMessageCodes.INVALID_CODE);
                }
            }
        }

        return errors;
    }
}
