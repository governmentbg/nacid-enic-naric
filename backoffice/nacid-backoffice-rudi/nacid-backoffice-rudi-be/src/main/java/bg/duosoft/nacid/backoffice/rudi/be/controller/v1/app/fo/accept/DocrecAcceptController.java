package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept.DocrecAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept.DocrecAcceptViewDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept.base.BaseAcceptController;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidservicesclient.client.ServicesDocDegreesClient;
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
@RequestMapping("/api/v1/fo-applications/accept/docrec")
public class DocrecAcceptController extends BaseAcceptController {

    private final ServicesDocDegreesClient servicesDocDegreesClient;


    @GetMapping("/initialize/{foAppId}")
    @ApiOperation(value = "Docrec accept e-apps initialization")
    public DocrecAcceptDTO initialize(@PathVariable Integer foAppId) {
        RudiApplicationDTO convertedApplication = convertToBackofficeApplication(selectFoApplication(foAppId));
        DocrecAcceptDTO acceptDTO = new DocrecAcceptDTO();
        acceptDTO.setViewData(fillViewData(convertedApplication));
        setInitialAcceptData(acceptDTO, convertedApplication);
        return acceptDTO;
    }


    @GetMapping("/check/{foAppId}")
    @ApiOperation(value = "Check if application meets requirements for accept")
    public void check(@PathVariable Integer foAppId) {
        DocDegreesApplicationDTO foApplication = selectFoApplication(foAppId);
        foAcceptAppRequirementsService.checkAcceptRequirements(foApplication, ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION);
    }

    @PostMapping("/{foAppId}")
    @ApiOperation(value = "Docrec application accept")
    public IntegerIdDTO accept(@PathVariable Integer foAppId, @RequestBody DocrecAcceptDTO requestData) {
        return processAcceptance(requestData, selectFoApplication(foAppId));
    }

    public void setInitialAcceptData(DocrecAcceptDTO docrecAcceptDTO, RudiApplicationDTO convertedApplication) {
        super.setInitialAcceptData(docrecAcceptDTO,convertedApplication);
        docrecAcceptDTO.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(convertedApplication.getApplication().getDocumentReceiveMethods()));
    }

    public DocrecAcceptViewDataDTO fillViewData(RudiApplicationDTO convertedApplication) {
        DocrecAcceptViewDataDTO viewData = new DocrecAcceptViewDataDTO();
        fillBaseViewData(convertedApplication, viewData);
        viewData.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(convertedApplication.getApplication().getDocumentReceiveMethods()));
        return viewData;
    }

    private DocDegreesApplicationDTO selectFoApplication(Integer foAppId) {
        return ResponseUtils.notFoundCheck(servicesDocDegreesClient.getApplication(foAppId));
    }

}
