package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept.DocrecAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.accept.SarAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.accept.SarAcceptViewDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept.base.BaseAcceptController;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidservicesclient.client.ServicesUniChecksClient;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.FO_APP_ACCEPT)
@RequestMapping("/api/v1/fo-applications/accept/sar")
public class SarAcceptController extends BaseAcceptController {

    private final ServicesUniChecksClient servicesUniChecksClient;

    @GetMapping("/initialize/{foAppId}")
    @ApiOperation(value = "Sar accept e-apps initialization")
    public SarAcceptDTO initialize(@PathVariable Integer foAppId) {
        RudiApplicationDTO convertedApplication = convertToBackofficeApplication(selectFoApplication(foAppId));

        SarAcceptDTO acceptDTO = new SarAcceptDTO();
        acceptDTO.setViewData(fillViewData(convertedApplication));
        setInitialAcceptData(acceptDTO, convertedApplication);
        setExistingDiplomaOwner(convertedApplication, acceptDTO);
        return acceptDTO;
    }

    @GetMapping("/check/{foAppId}")
    @ApiOperation(value = "Check if application meets requirements for accept")
    public void check(@PathVariable Integer foAppId) {
        UniChecksApplicationDTO foApplication = selectFoApplication(foAppId);
        foAcceptAppRequirementsService.checkAcceptRequirements(foApplication, ApplicationSubType.RUDI_SAR);
    }

    @PostMapping("/{foAppId}")
    @ApiOperation(value = "Sar application accept")
    public IntegerIdDTO accept(@PathVariable Integer foAppId, @RequestBody SarAcceptDTO requestData) {
        return processAcceptance(requestData, selectFoApplication(foAppId));
    }


    public void setInitialAcceptData(SarAcceptDTO sarAcceptDTO, RudiApplicationDTO convertedApplication) {
        super.setInitialAcceptData(sarAcceptDTO,convertedApplication);
        sarAcceptDTO.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(convertedApplication.getApplication().getDocumentReceiveMethods()));
    }

    public SarAcceptViewDataDTO fillViewData(RudiApplicationDTO convertedApplication) {

        SarAcceptViewDataDTO viewData = new SarAcceptViewDataDTO();
        fillBaseViewData(convertedApplication, viewData);
        viewData.setDiplomaOwner(convertedApplication.getTrainingCourse().getDiplomaOwner());
        viewData.setTrainingCourseSpecialities(convertedApplication.getTrainingCourse().getTrainingCourseSpecialities());
        viewData.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(convertedApplication.getApplication().getDocumentReceiveMethods()));
        return viewData;
    }

    private UniChecksApplicationDTO selectFoApplication(Integer foAppId) {
        return ResponseUtils.notFoundCheck(servicesUniChecksClient.getApplication(foAppId));
    }

    private void setExistingDiplomaOwner(RudiApplicationDTO convertedApplication, SarAcceptDTO acceptDTO) {
        TrainingCourseDTO trainingCourse = convertedApplication.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            PersonDTO diplomaOwner = getExistingBackofficePerson(trainingCourse.getDiplomaOwner());
            if (Objects.nonNull(diplomaOwner)) {
                acceptDTO.setDiplomaOwnerId(diplomaOwner.getId());
            }
        }
    }


}
