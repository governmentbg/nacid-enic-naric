package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.reception.DocrecReceptionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.reception.base.ReceptionBaseController;
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
@Api(tags = Tags.RECEPTION)
@RequestMapping("/api/v1/applications/reception/docrec")
public class DocrecReceptionController extends ReceptionBaseController {

    @GetMapping("/initialize")
    @ApiOperation(value = "Docrec application init")
    public DocrecReceptionDTO initialize() {
        DocrecReceptionDTO initialData = new DocrecReceptionDTO();
        initialData.setDiffDiplomaNamesFlag(false);
        initialData.setDocumentReceiveMethod(new DocumentReceiveMethodFormDTO());
        initialData.getDocumentReceiveMethod().setCrfCodes(List.of(CertificateReceiveFormType.PAPER.code()));
        return initialData;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Docrec application create")
    public IntegerIdDTO create(@RequestBody DocrecReceptionDTO requestData) {
        return processReception(requestData);
    }

}
