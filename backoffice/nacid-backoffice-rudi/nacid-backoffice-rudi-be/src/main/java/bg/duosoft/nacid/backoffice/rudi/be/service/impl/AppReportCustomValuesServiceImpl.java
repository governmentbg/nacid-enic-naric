package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacid.backoffice.rudi.be.service.AppReportCustomValuesService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AppReportCustomValuesServiceImpl implements AppReportCustomValuesService {
    private final RudiApplicationService rudiApplicationService;
    private final CommissionCalendarService commissionCalendarService;

    @Override
    public String getCertificateNumber(Integer applicationId) {
        RudiApplicationDTO application = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }

        ApplicationSubtypeDTO applicationSubtype = application.getApplication().getApplicationSubtype();
        if (applicationSubtype.getId().equals(ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION.appSubType())) {
            return getUdirecCertificateNumber(application);
        }

        if (applicationSubtype.getId().equals(ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION.appSubType())) {
            return getDocDegreeRecognitionCertificateNumber(application);
        }

        return null;
    }


    private String getDocDegreeRecognitionCertificateNumber(RudiApplicationDTO application) {
        return application.getApplication().getEntryNumber().concat("/").concat(DateUtils.formatLocalDate(LocalDate.now()));
    }
    private String getUdirecCertificateNumber(RudiApplicationDTO application) {
        Integer applicationId = application.getApplication().getId();
        Integer calendarNumber = commissionCalendarService.selectLastCommissionSessionNumByApnId(applicationId);
        if (Objects.isNull(calendarNumber)) {
            throw new RuntimeException("Calendar for application with id: " + applicationId + " is not found!");
        }
        String entryDateAsStr = String.valueOf(application.getApplication().getEntryDate().getYear());
        String entryNumLastPart = CommonUtils.appEntryNumLastPart(application.getApplication().getEntryNumber());
        return entryDateAsStr.concat("-").concat(entryNumLastPart).concat("-").concat(String.valueOf(calendarNumber)).concat("/").concat(DateUtils.formatLocalDate(LocalDate.now()));
    }


}
