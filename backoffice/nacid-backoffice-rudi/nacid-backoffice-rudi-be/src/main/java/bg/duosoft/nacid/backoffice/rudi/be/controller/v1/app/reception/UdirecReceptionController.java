package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.reception.UdirecReceptionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.reception.base.ReceptionBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.RECEPTION)
@RequestMapping("/api/v1/applications/reception/udirec")
public class UdirecReceptionController extends ReceptionBaseController {

    @GetMapping("/initialize")
    @ApiOperation(value = "Udirec application init")
    public UdirecReceptionDTO initialize() {
        UdirecReceptionDTO initialData = new UdirecReceptionDTO();
        initialData.setTrainingCourseSpecialities(new ArrayList<>());
        initialData.setDiffDiplomaNamesFlag(false);
        initialData.setDocumentReceiveMethod(new DocumentReceiveMethodFormDTO());
        initialData.getDocumentReceiveMethod().setCrfCodes(List.of(CertificateReceiveFormType.PAPER.code()));
        return initialData;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Udirec application create")
    public IntegerIdDTO create(@RequestBody UdirecReceptionDTO requestData) {
        return processReception(requestData);
    }

}
