package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.ProfGroupService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfGroupDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfGroupFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 15:08
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_PROF_GROUP)
@RequestMapping("/api/v1/prof-group")
public class ProfGroupController extends NomenclatureSearchBaseController<Integer, ProfGroupDTO, ProfGroupFilterDTO> {

    private final ProfGroupService service;

    @Override
    protected NomenclatureServiceBase<Integer, ProfGroupDTO, ProfGroupFilterDTO> getService() {
        return service;
    }
}
