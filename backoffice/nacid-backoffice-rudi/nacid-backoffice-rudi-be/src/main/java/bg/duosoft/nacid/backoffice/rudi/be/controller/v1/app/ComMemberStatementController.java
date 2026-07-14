package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberStatementDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationCommissionMemberStatementService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsAutoFileTransferService;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_COMMISSION_MEMBER_STATEMENT)
@RequestMapping("/api/v1/applications/commission-member-statements")
public class ComMemberStatementController extends BaseAccessController {
    private final ApplicationCommissionMemberStatementService applicationCommissionMemberStatementService;
    private final AbdocsAutoFileTransferService abdocsAutoFileTransferService;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }


    @GetMapping(value = "/by-application/{applicationId}")
    @ApiOperation(value = "select application member statements by application id")
    public List<ApplicationCommissionMemberStatementDTO> selectByApplicationId(@PathVariable Integer applicationId) {
        return applicationCommissionMemberStatementService.selectByApplicationId(applicationId);
    }

    @GetMapping(value = "/statement/{id}")
    @ApiOperation(value = "select application commission member statement by id")
    public ApplicationCommissionMemberStatementDTO selectById(@PathVariable Integer id) {
        ApplicationCommissionMemberStatementDTO applicationCommissionMemberDTO = applicationCommissionMemberStatementService.selectById(id);
        return applicationCommissionMemberDTO;
    }

    @PostMapping(value = "save/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Save application commission member statement data")
    public void saveApplicationCommissionMemberData(@PathVariable Integer applicationId, @RequestBody ApplicationCommissionMemberStatementDTO requestData) {
        RudiApplicationDTO rudiApplication = applicationCommissionMemberStatementService.saveApplicationCommissionMemberData(applicationId, requestData);

        List<ApplicationCommissionMemberStatementDTO> applicationCommissionMemberStatements = rudiApplication.getApplicationCommissionMemberStatements();
        if (!CollectionUtils.isEmpty(applicationCommissionMemberStatements)) {
            List<AttachedDocDTO> transferDocs = applicationCommissionMemberStatements.stream()
                    .map(ApplicationCommissionMemberStatementDTO::getAttachedDoc)
                    .filter(Objects::nonNull)
                    .filter(a -> !StringUtils.hasText(a.getDocflowId()))
                    .toList();

            abdocsAutoFileTransferService.transferFiles(applicationId, transferDocs);
        }
    }

    @DeleteMapping({"/{id}"})
    @ApiOperation("Delete value")
    public void delete(@PathVariable("id") Integer id) {
        this.applicationCommissionMemberStatementService.delete(id);
    }
}

