package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSubsectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.uni.UniExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.uni.UniExamDataSubsectionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniExamDataService;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityExaminationService;
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
@RequestMapping("/api/v1/applications/data/status/uni-exam")
public class UniExamDataController extends RudiAppDataBaseController {
    private final UniversityExaminationService universityExaminationService;
    private final UniExamDataService uniExamDataService;
    private final UniExamDataMapper uniExamDataMapper;
    private final UniExamDataSubsectionMapper uniExamDataSubsectionMapper;
    private final AbdocsAutoFileTransferService abdocsAutoFileTransferService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select application university examination data")
    public UniExamSectionDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO application = selectOriginalApplication(id);
        return uniExamDataMapper.toUniExamSection(application);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Update rudi university examination data")
    public UniExamSubsectionDTO updateUniExaminationData(@PathVariable Integer id, @RequestBody UniExamSubsectionDTO universityExamination) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        UniExamSubsectionDTO uniExamSubsection = uniExamDataService.saveUniExamData(universityExamination, app);
        if (Objects.nonNull(uniExamSubsection)) {
            List<AttachedDocDTO> attachedDocs = uniExamSubsection.getAttachedDocs();
            abdocsAutoFileTransferService.transferFiles(id, attachedDocs);
        }
        return uniExamSubsection;
    }

    @GetMapping(value = "/{id}/exam/{uniExamId}")
    @ApiOperation(value = "Select university examination subsection data")
    public UniExamSubsectionDTO selectResetUniversityExaminationData(@PathVariable("id") Integer applicationId, @PathVariable("uniExamId") Integer uniExaminationId) {
        RudiApplicationDTO app = selectOriginalApplication(applicationId);
        TrainingCourseUniversityExaminationDTO universityExamination = universityExaminationService.selectById(uniExaminationId);
        UniExamSubsectionDTO uniExamSubsection = uniExamDataSubsectionMapper.toUniExamSubsection(universityExamination, true);
        uniExamDataService.overrideAttachedDocsWithOriginal(uniExamSubsection, app);
        return uniExamSubsection;
    }
}
