package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.ProfExperienceDocTypeService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfExperienceDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfExperienceDocTypeFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_PROF_EXPERIENCE_DOC_TYPE)
@RequestMapping("/api/v1/prof-experience-doc-type")
public class ProfExperienceDocTypeController extends NomenclatureSearchBaseController<String, ProfExperienceDocTypeDTO, ProfExperienceDocTypeFilterDTO> {

    private final ProfExperienceDocTypeService service;

    @Override
    protected NomenclatureServiceBase<String, ProfExperienceDocTypeDTO, ProfExperienceDocTypeFilterDTO> getService() {
        return service;
    }
}
