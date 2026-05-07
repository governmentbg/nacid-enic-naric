package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.DocumentReceiveMethodService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocumentReceiveMethodDataFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_DOCUMENT_RECEIVE_METHOD)
@RequestMapping("/api/v1/document-receive-methods")
public class DocumentReceiveMethodController extends NomenclatureSearchBaseController<String, DocumentReceiveMethodDTO, DocumentReceiveMethodDataFilterDTO> {

    private final DocumentReceiveMethodService service;

    @Override
    protected DocumentReceiveMethodService getService() {
        return service;
    }

}
