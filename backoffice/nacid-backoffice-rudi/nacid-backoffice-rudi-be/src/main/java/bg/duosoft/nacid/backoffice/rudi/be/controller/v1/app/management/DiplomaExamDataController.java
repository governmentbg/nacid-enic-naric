package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.diploma.DiplomaExamSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.diploma.DiplomaExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.DiplomaExamDataService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsAutoFileTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_STATUS)
@RequestMapping("/api/v1/applications/data/status/diploma-exam")
public class DiplomaExamDataController extends RudiAppDataBaseController {

    private final DiplomaExamDataMapper diplomaExamDataMapper;
    private final DiplomaExamDataService diplomaExamDataService;
    private final AbdocsAutoFileTransferService abdocsAutoFileTransferService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select application diploma examination data")
    public DiplomaExamSectionDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO application = selectOriginalApplication(id);
        return diplomaExamDataMapper.toDiplomaExamSection(application);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Update rudi diploma examination data")
    public DiplomaExamSectionDTO updateDiplomaExamData(@PathVariable Integer id, @RequestBody DiplomaExamSectionDTO diplomaExamination) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        DiplomaExamSectionDTO updatedDiplomaExamSection = diplomaExamDataService.saveDiplomaExamData(diplomaExamination, app);
        if (Objects.nonNull(updatedDiplomaExamSection)) {
            List<AttachedDocDTO> attachedDocs = updatedDiplomaExamSection.getAttachedDocs();
            abdocsAutoFileTransferService.transferFiles(id, attachedDocs);
        }
        return updatedDiplomaExamSection;
    }
}
