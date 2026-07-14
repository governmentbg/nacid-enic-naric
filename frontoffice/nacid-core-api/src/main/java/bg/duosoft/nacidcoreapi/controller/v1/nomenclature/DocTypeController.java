package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.DocTypeService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocTypeFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 11:12
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_DOC_TYPE)
@RequestMapping("/api/v1/doc-type")
public class DocTypeController extends NomenclatureSearchBaseController<Integer, DocTypeDTO, DocTypeFilterDTO> {

    private final DocTypeService service;

    @Override
    protected NomenclatureServiceBase<Integer, DocTypeDTO, DocTypeFilterDTO> getService() {
        return service;
    }
}
