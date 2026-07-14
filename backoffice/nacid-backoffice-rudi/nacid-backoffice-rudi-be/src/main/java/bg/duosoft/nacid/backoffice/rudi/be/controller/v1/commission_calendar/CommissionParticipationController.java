package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionParticipationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationCustomDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationSaveDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionMemberService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionParticipationService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.CrudController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.COMMISSION_PARTICIPATION)
@RequestMapping("/api/v1/commission-participations")
public class CommissionParticipationController extends CrudController<Integer, CommissionParticipationDTO> {
    private final CommissionParticipationService commissionParticipationService;
    private final CommissionMemberService commissionMemberService;

    @Override
    protected CommissionParticipationService getService() {
        return commissionParticipationService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.COMMISSION_CALENDAR_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.COMMISSION_CALENDAR_ACCESS;
    }


    @GetMapping(value = "/members/calendar/{calendarId}")
    @ApiOperation(value = "Select members by calendar id")
    public List<CommissionCalendarParticipationCustomDTO> selectAllIdsByCalendarId(@PathVariable("calendarId") Integer calendarId) {
        List<CommissionCalendarParticipationCustomDTO> members = new ArrayList<>();
        List<CommissionParticipationDTO> commissionParticipationDTOList = commissionParticipationService.selectByCalendarId(calendarId);

        if (!CollectionUtils.isEmpty(commissionParticipationDTOList)) {
            members = commissionParticipationDTOList.stream().map(r -> {
                return new CommissionCalendarParticipationCustomDTO(r.getCommissionMember(), r.getNotified(), r.getParticipated(), r.getChairman());
            }).collect(Collectors.toList());
        }

        return members;
    }

    @GetMapping(value = "/members")
    @ApiOperation(value = "Select members by ids")
    public List<CommissionCalendarParticipationCustomDTO> selectMembersByIds(@RequestParam("ids") List<Integer> ids) {
        List<CommissionCalendarParticipationCustomDTO> members = new ArrayList<>();
        List<CommissionMemberDTO> commissionParticipationDTOList = commissionMemberService.selectMembersByIds(ids);

        if (!CollectionUtils.isEmpty(commissionParticipationDTOList)) {
            members = commissionParticipationDTOList.stream().map(r -> {
                return new CommissionCalendarParticipationCustomDTO(r, false, false, false);
            }).collect(Collectors.toList());
        }

        return members;
    }

    @PostMapping(value = "/save")
    @ApiOperation("Insert members")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).COMMISSION_CALENDAR_EDIT)")
    public List<CommissionCalendarParticipationCustomDTO> saveMembers(@RequestBody CommissionCalendarParticipationSaveDTO dto) {
        commissionParticipationService.saveMembers(dto);
        return dto.getParticipations();
    }
}
