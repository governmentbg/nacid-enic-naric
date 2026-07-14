package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.accept.UdirecAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.accept.UdirecAcceptViewDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept.base.BaseAcceptController;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidservicesclient.client.ServicesHeRecognitionClient;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.FO_APP_ACCEPT)
@RequestMapping("/api/v1/fo-applications/accept/udirec")
public class UdirecAcceptController extends BaseAcceptController {

    private final ServicesHeRecognitionClient servicesHeRecognitionClient;

    @GetMapping("/initialize/{foAppId}")
    @ApiOperation(value = "Udirec accept e-apps initialization")
    public UdirecAcceptDTO initialize(@PathVariable Integer foAppId) {
        RudiApplicationDTO convertedApplication = convertToBackofficeApplication(selectFoApplication(foAppId));

        UdirecAcceptDTO acceptDTO = new UdirecAcceptDTO();
        acceptDTO.setViewData(fillViewData(convertedApplication));
        setInitialAcceptData(acceptDTO, convertedApplication);
        return acceptDTO;
    }

    @GetMapping("/check/{foAppId}")
    @ApiOperation(value = "Check if application meets requirements for accept")
    public void check(@PathVariable Integer foAppId) {
        HeRecognitionApplicationDTO foApplication = selectFoApplication(foAppId);
        foAcceptAppRequirementsService.checkAcceptRequirements(foApplication, ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION);
    }

    @PostMapping("/{foAppId}")
    @ApiOperation(value = "Udirec application accept")
    public IntegerIdDTO accept(@PathVariable Integer foAppId, @RequestBody UdirecAcceptDTO requestData) {
        return processAcceptance(requestData, selectFoApplication(foAppId));
    }

    public void setInitialAcceptData(UdirecAcceptDTO udirecAcceptDTO, RudiApplicationDTO convertedApplication) {
        super.setInitialAcceptData(udirecAcceptDTO,convertedApplication);
        udirecAcceptDTO.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(convertedApplication.getApplication().getDocumentReceiveMethods()));
    }

    public UdirecAcceptViewDataDTO fillViewData(RudiApplicationDTO convertedApplication) {

        UdirecAcceptViewDataDTO viewData = new UdirecAcceptViewDataDTO();
        fillBaseViewData(convertedApplication, viewData);
        viewData.setTrainingCourseSpecialities(convertedApplication.getTrainingCourse().getTrainingCourseSpecialities());
        viewData.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(convertedApplication.getApplication().getDocumentReceiveMethods()));
        return viewData;
    }

    private HeRecognitionApplicationDTO selectFoApplication(Integer foAppId) {
        return ResponseUtils.notFoundCheck(servicesHeRecognitionClient.getApplication(foAppId));
    }

}
