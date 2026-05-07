package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.summary.DocrecSummaryDTO;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.SUMMARY)
@RequestMapping("/api/v1/applications/summary/docrec")
public class DocrecSummaryController extends BaseSummaryController {

    @GetMapping(value = "/{applicationId}")
    @ApiOperation(value = "Get docrec summary")
    public DocrecSummaryDTO getSummary(@PathVariable Integer applicationId) {
        RudiApplicationDTO app = selectApplicationById(applicationId);
        DocrecSummaryDTO summary = new DocrecSummaryDTO();
        setBaseSummary(summary, app);
        return summary;
    }

}
