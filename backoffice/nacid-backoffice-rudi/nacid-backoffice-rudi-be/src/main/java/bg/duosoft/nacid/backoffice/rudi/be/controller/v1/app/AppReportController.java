package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.AppReportCustomValuesService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.RudiApplicationReportValidator;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_REPORT)
@RequestMapping("/api/v1/applications/app-report")
public class AppReportController extends BaseAccessController {

    private final RudiApplicationReportValidator applicationReportValidator;
    private final RudiApplicationService rudiApplicationService;
    private final AppReportCustomValuesService appReportCustomValuesService;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }

    @GetMapping(value = "/generate/errors")
    @ApiOperation(value = "Get errors on generate report")
    public List<ValidationError> getErrorsOnGenerateReport(@RequestParam("applicationId") Integer applicationId,
                                                           @RequestParam("documentTypeId") Integer documentTypeId,
                                                           @RequestParam(required = false, value = "attachmentId") Integer attachmentId) {
        RudiApplicationDTO application = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }
        return applicationReportValidator.validate(application.getApplication(), applicationReportValidator.initAttachmentBeforeValidation(documentTypeId, attachmentId));
    }


    @GetMapping(value = "/certificate-number")
    @ApiOperation(value = "Get certificate number")
    public String getCertificateNumber(@RequestParam("applicationId") Integer applicationId) {
        return appReportCustomValuesService.getCertificateNumber(applicationId);
    }

}
