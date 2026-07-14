package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.RudiEduDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.education.DocrecEduDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.education.SarEduDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.education.UdirecEduDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec.DocrecEduDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar.SarEduDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec.UdirecEduDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidfrontofficedto.services.common.education.UniversityDataDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidservicesclient.client.ServicesDocDegreesClient;
import bg.duosoft.nacidservicesclient.client.ServicesHeRecognitionClient;
import bg.duosoft.nacidservicesclient.client.ServicesUniChecksClient;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATIONS)
@RequestMapping("/api/v1/applications/data/education")
public class EduDataController extends RudiAppDataBaseController {

    private final SarEduDataMapper sarEduDataMapper;
    private final UdirecEduDataMapper udirecEduDataMapper;
    private final DocrecEduDataMapper docrecEduDataMapper;

    private final ServicesDocDegreesClient servicesDocDegreesClient;
    private final ServicesUniChecksClient servicesUniChecksClient;
    private final ServicesHeRecognitionClient servicesHeRecognitionClient;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select rudi education data")
    public RudiEduDataBaseDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO app = selectOriginalApplication(id);

        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(app.getApplication().getApplicationType().getId(), app.getApplication().getApplicationSubtype().getId());
        switch (type) {
            case RUDI_SAR -> {
                return sarEduDataMapper.toEducationDataSection(app);
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                return udirecEduDataMapper.toEducationDataSection(app);
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                return docrecEduDataMapper.toEducationDataSection(app);
            }
            default -> throw new ResourceNotFoundException();
        }
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Update rudi education data")
    public RudiEduDataBaseDTO updateApplicationEducationData(@PathVariable Integer id, @RequestBody String requestData) {
        RudiApplicationDTO app = selectOriginalApplication(id);

        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(app.getApplication().getApplicationType().getId(), app.getApplication().getApplicationSubtype().getId());
        switch (type) {
            case RUDI_SAR -> {
                SarEduDataDTO sarEduDataDTO = jsonUtil.readJson(requestData, SarEduDataDTO.class);
                sarEduDataMapper.overrideApplicationData(sarEduDataDTO, app);
                RudiApplicationDTO saved = rudiApplicationService.save(app, ValidationScope.EDUCATION_DATA);
                return sarEduDataMapper.toEducationDataSection(saved);
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                UdirecEduDataDTO udirecEduDataDTO = jsonUtil.readJson(requestData, UdirecEduDataDTO.class);
                udirecEduDataMapper.overrideApplicationData(udirecEduDataDTO, app);
                RudiApplicationDTO saved = rudiApplicationService.save(app, ValidationScope.EDUCATION_DATA);
                return udirecEduDataMapper.toEducationDataSection(saved);
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                DocrecEduDataDTO docrecEduDataDTO = jsonUtil.readJson(requestData, DocrecEduDataDTO.class);
                docrecEduDataMapper.overrideApplicationData(docrecEduDataDTO, app);
                RudiApplicationDTO saved = rudiApplicationService.save(app, ValidationScope.EDUCATION_DATA);
                return docrecEduDataMapper.toEducationDataSection(saved);
            }
            default -> throw new ResourceNotFoundException();
        }
    }

    @GetMapping(value = "/notes/{id}")
    @ApiOperation(value = "Select education data notes")
    public List<String> selectNotesById(@PathVariable Integer id) {
        RudiApplicationDTO application = selectOriginalApplication(id);
        List<ApplicationNotesDTO> applicationNotes = application.getApplication().getApplicationNotes();
        return applicationNotes.stream().map(ApplicationNotesDTO::getNote).toList();
    }

    @GetMapping(value = "/check-unfilled-universities/{id}")
    @ApiOperation(value = "Check for unfilled universities from fo app")
    public List<UniversityDataDTO> checkUnfilledUniversities(@PathVariable Integer id) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        Integer efilingId = app.getApplication().getEfilingId();
        if (Objects.isNull(efilingId)) {
            return null;
        }
        List<UniversityDataDTO> universitiesData;
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(app.getApplication().getApplicationType().getId(), app.getApplication().getApplicationSubtype().getId());
        switch (type) {
            case RUDI_SAR -> {
                UniChecksApplicationDTO foApp = servicesUniChecksClient.getApplication(efilingId);
                if (Objects.isNull(foApp.getEducationDetails()) || Objects.isNull(foApp.getEducationDetails().getUniversitiesData())) {
                    return null;
                }
                universitiesData = foApp.getEducationDetails().getUniversitiesData();
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                HeRecognitionApplicationDTO foApp = servicesHeRecognitionClient.getApplication(efilingId);
                if (Objects.isNull(foApp.getEducationDetails()) || Objects.isNull(foApp.getEducationDetails().getUniversitiesData())) {
                    return null;
                }
                universitiesData = foApp.getEducationDetails().getUniversitiesData();
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                DocDegreesApplicationDTO foApp = servicesDocDegreesClient.getApplication(efilingId);
                if (Objects.isNull(foApp.getEducationDetails()) || Objects.isNull(foApp.getEducationDetails().getUniversitiesData())) {
                    return null;
                }
                universitiesData = foApp.getEducationDetails().getUniversitiesData();
            }
            default -> throw new ResourceNotFoundException();
        }

        if (!CollectionUtils.isEmpty(universitiesData) && universitiesData.stream().anyMatch(x -> Objects.isNull(x.getNameId()))) {
            List<TrainingCourseUniversityDTO> boAppUniversities = app.getTrainingCourse().getTrainingCourseUniversities();
            if (CollectionUtils.isEmpty(boAppUniversities)) {
                return universitiesData;
            }
            return universitiesData.size() > boAppUniversities.size() ? universitiesData : null;
        }
        return null;
    }

}
