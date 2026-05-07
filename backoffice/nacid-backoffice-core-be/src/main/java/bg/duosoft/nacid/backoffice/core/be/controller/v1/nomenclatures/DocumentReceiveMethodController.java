package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.DocumentReceiveMethodService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentReceiveMethodFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_DOCUMENT_RECEIVE_METHOD)
@RequestMapping("/api/v1/document-receive-methods")
public class DocumentReceiveMethodController extends NomenclatureBaseController<String, DocumentReceiveMethodDTO, DocumentReceiveMethodFilterDTO> {

    private final DocumentReceiveMethodService service;

    @Override
    protected DocumentReceiveMethodService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping
    @ApiOperation("Select all nomenclature records")
    public List<DocumentReceiveMethodDTO> getAll(@RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        List<DocumentReceiveMethodDTO> result = this.getService().selectAll(onlyActive);
        if (CollectionUtils.isEmpty(result)) {
            throw new ResourceNotFoundException();
        }
        result.sort(Comparator.comparing(DocumentReceiveMethodDTO::getIndex));
        return result;
    }

}
