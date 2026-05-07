package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.TrainingLocationExaminationUniversityDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.training_location.TrainingLocationExamSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.location.TrainingLocationExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.TrainingLocationExamService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_STATUS)
@RequestMapping("/api/v1/applications/data/status/training-location-exam")
public class TrainingLocationExamController extends RudiAppDataBaseController {
    private final TrainingLocationExamService trainingLocationExamService;
    private final TrainingLocationExamDataMapper trainingLocationExamDataMapper;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select application training location examination data")
    public TrainingLocationExamSectionDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO application = selectOriginalApplication(id);
        return trainingLocationExamDataMapper.toTrainingLocationExamSection(application);
    }


    @GetMapping(value = "/universities/{id}")
    @ApiOperation(value = "Select application training location examination data")
    public List<TrainingLocationExaminationUniversityDataDTO> selectUniversitiesInfo(@PathVariable Integer id) {
        return trainingLocationExamService.selectUniversitiesSubsectionInfo(selectOriginalApplication(id));
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Update rudi application training location examination data")
    public TrainingLocationExamSectionDTO updateTrainingLocationExamData(@PathVariable Integer id, @RequestBody TrainingLocationExamSectionDTO trainingLocationExamination) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        return trainingLocationExamService.saveTrainingLocationExamData(trainingLocationExamination, app);
    }
}
