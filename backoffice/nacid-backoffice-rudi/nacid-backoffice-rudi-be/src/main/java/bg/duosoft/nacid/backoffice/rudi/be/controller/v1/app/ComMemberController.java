package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationCommissionMemberService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_COMMISSION_MEMBER)
@RequestMapping("/api/v1/applications/commission-members")
public class ComMemberController extends BaseAccessController {
    private final ApplicationCommissionMemberService applicationCommissionMemberService;
    private final RudiApplicationService rudiApplicationService;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }


    @GetMapping(value = "/by-application/{applicationId}")
    @ApiOperation(value = "select application members by application id")
    public List<ApplicationCommissionMemberDTO> selectByApplicationId(@PathVariable Integer applicationId) {
        return applicationCommissionMemberService.selectByApplicationId(applicationId);
    }

    @GetMapping(value = "/member/{id}")
    @ApiOperation(value = "select application commission member by id")
    public ApplicationCommissionMemberDTO selectById(@PathVariable Integer id) {
        ApplicationCommissionMemberDTO applicationCommissionMemberDTO = applicationCommissionMemberService.selectById(id);
        return applicationCommissionMemberDTO;
    }

    @PostMapping(value = "save/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Save application commission members data")
    public void saveApplicationCommissionMemberData(@PathVariable Integer applicationId, @RequestBody ApplicationCommissionMemberDTO requestData) {
        RudiApplicationDTO application = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }

        if (CollectionUtils.isEmpty(application.getApplicationCommissionMembers())){
            application.setApplicationCommissionMembers(new ArrayList<>());
        }
        List<ApplicationCommissionMemberDTO> applicationCommissionMembers = application.getApplicationCommissionMembers();
        if (Objects.nonNull(requestData.getId())) {
            ApplicationCommissionMemberDTO existedRecord = applicationCommissionMembers.stream().filter(r -> r.getId().equals(requestData.getId())).findFirst().orElse(null);
            int existedRecordIndex = applicationCommissionMembers.indexOf(existedRecord);
            applicationCommissionMembers.set(existedRecordIndex,requestData);
        } else {
            applicationCommissionMembers.add(requestData);
        }
        rudiApplicationService.save(application, ValidationScope.COMMISSION_MEMBER);
    }

    @DeleteMapping({"/{id}"})
    @ApiOperation("Delete value")
    public void delete(@PathVariable("id") Integer id) {
        this.applicationCommissionMemberService.delete(id);
    }
}
