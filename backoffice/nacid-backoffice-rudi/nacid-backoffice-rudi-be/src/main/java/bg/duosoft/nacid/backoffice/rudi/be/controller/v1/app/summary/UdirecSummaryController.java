package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.summary.UdirecSummaryDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.SUMMARY)
@RequestMapping("/api/v1/applications/summary/udirec")
public class UdirecSummaryController extends BaseSummaryController {

    private final CommissionApplicationService commissionApplicationService;
    private final CommissionCalendarService commissionCalendarService;

    @GetMapping(value = "/{applicationId}")
    @ApiOperation(value = "Get udirec summary")
    public UdirecSummaryDTO getSummary(@PathVariable Integer applicationId) {
        RudiApplicationDTO app = selectApplicationById(applicationId);
        UdirecSummaryDTO summary = new UdirecSummaryDTO();
        setBaseSummary(summary, app);
        summary.setCalendars(getCalendarNumbers(applicationId));
        return summary;
    }


    private String getCalendarNumbers(Integer applicationId) {
        StringBuilder calendarNumberBuilder = new StringBuilder();
        List<CommissionApplicationDTO> commissionApplications = commissionApplicationService.selectByApplicationId(applicationId);

        if (!CollectionUtils.isEmpty(commissionApplications)) {
            commissionApplications.sort(Comparator.comparing(CommissionApplicationDTO::getCalendarId).reversed());
            for (int i = 0; i < commissionApplications.size(); i++) {
                String calendarFullNumber = commissionCalendarService.getFullNumber(commissionApplications.get(i).getCalendarId());
                calendarNumberBuilder.append(calendarFullNumber);
                if (i < commissionApplications.size() - 1) {
                    calendarNumberBuilder.append(", ");
                }
            }
        }

        return !StringUtils.hasText(calendarNumberBuilder.toString()) ? null : calendarNumberBuilder.toString();
    }

}
