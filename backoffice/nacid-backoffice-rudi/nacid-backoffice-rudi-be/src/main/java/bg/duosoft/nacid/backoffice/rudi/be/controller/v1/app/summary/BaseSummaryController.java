package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.summary;

import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsUrlBuilder;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary.BaseAppSummaryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.summary.RudiSummaryDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.SummaryUtils;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
public abstract class BaseSummaryController {

    @Autowired
    protected RudiApplicationService rudiApplicationService;

    @Autowired
    private AbdocsUrlBuilder abdocsUrlBuilder;

    public RudiApplicationDTO selectApplicationById(Integer applicationId) {
        RudiApplicationDTO app = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(app)) {
            throw new ResourceNotFoundException();
        }
        return app;
    }

    public void setBaseSummary(RudiSummaryDTO summary, RudiApplicationDTO app) {
        ApplicationDTO application = app.getApplication();
        if (Objects.nonNull(app.getApplication().getServiceType()) && StringUtils.hasText(app.getApplication().getServiceType().getId())) {
            summary.setServiceTypeCode(app.getApplication().getServiceType().getId());
        }
        summary.setId(application.getId());
        summary.setEntryNum(application.getEntryNumber());
        summary.setEntryDate(application.getEntryDate());
        summary.setApplicant(SummaryUtils.getApplicantName(application));
        summary.setResponsibleUser(SummaryUtils.getResponsibleUser(application));
        summary.setStatus(SummaryUtils.getStatusName(application));
        summary.setDocflowStatus(SummaryUtils.getDocflowStatusName(application));
        summary.setDocflowDocumentUrl(SummaryUtils.getDocflowDocumentUrl(application, abdocsUrlBuilder));
        summary.setEfilingId(application.getEfilingId());
        summary.setBackofficeDate(application.getDateCreated().toLocalDate());
    }

}
