package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.program.ProgramExamSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.program.ProgramExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.ProgramExamDataService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_STATUS)
@RequestMapping("/api/v1/applications/data/status/program-exam")
public class ProgramExamDataController extends RudiAppDataBaseController {

    private final ProgramExamDataService programExamDataService;
    private final ProgramExamDataMapper programExamDataMapper;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select application university examination data")
    public ProgramExamSectionDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO application = selectOriginalApplication(id);
        return programExamDataMapper.toProgramExamSection(application);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Update rudi program examination data")
    public ProgramExamSectionDTO updateProgramExamData(@PathVariable Integer id, @RequestBody ProgramExamSectionDTO programExamination) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        return programExamDataService.saveProgramExamData(programExamination, app);
    }
}
