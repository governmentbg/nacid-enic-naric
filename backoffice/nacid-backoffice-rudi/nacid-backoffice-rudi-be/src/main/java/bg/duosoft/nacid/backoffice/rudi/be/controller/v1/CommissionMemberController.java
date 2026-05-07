package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.autocomplete.CommissionMemberAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionMemberFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionMemberService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.CrudController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.COMMISSION_MEMBER)
@RequestMapping("/api/v1/commission-members")
public class CommissionMemberController extends CrudController<Integer, CommissionMemberDTO> {

    private final CommissionMemberService service;

    @Override
    protected CommissionMemberService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.BO_NOMENCLATURES_ACCESS;
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "Filter records")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).BO_NOMENCLATURES_ACCESS)")
    public Page<CommissionMemberDTO> searchData(@RequestBody CommissionMemberFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CommissionMemberDTO> results = service.searchRecords(filter);
        return new Page<>(service.getRecordsCount(filter), results, filter.getPageSize());
    }

    //I added GET search method because that's how core-fe nomenclatures works
    //I did not delete the POST search because it is used in rudi-fe
    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter records")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).BO_NOMENCLATURES_ACCESS)")
    public Page<CommissionMemberDTO> searchDataGet(CommissionMemberFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CommissionMemberDTO> results = service.searchRecords(filter);
        return new Page<>(service.getRecordsCount(filter), results, filter.getPageSize());
    }

    @PostMapping({"/autocomplete"})
    @ApiOperation("Select commission members")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).BO_NOMENCLATURES_ACCESS)")
    public List<CommissionMemberAutocompleteDTO> searchMembers(@RequestBody CommissionMemberFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CommissionMemberDTO> commissionMemberDTOS = service.searchRecords(filter);
        return commissionMemberDTOS.stream().map(member -> CommissionMemberAutocompleteDTO.newInstance(member.getId(), member.getFirstName(), member.getMiddleName(), member.getLastName(), member.getIsActive())).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/toggle-activation/{id}")
    @ApiOperation(value = "Toggle activation")
    public void toggleActivation(@PathVariable("id") Integer id) {
        service.toggleActivation(id);
    }
}
