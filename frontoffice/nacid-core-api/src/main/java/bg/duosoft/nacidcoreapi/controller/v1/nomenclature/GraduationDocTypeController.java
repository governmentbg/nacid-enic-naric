package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.GraduationDocTypeService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.nomenclature.GraduationDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 11:02
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_GRADUATION_DOC_TYPE)
@RequestMapping("/api/v1/graduation-doc-type")
public class GraduationDocTypeController extends NomenclatureSearchBaseController<Integer, GraduationDocTypeDTO, GraduationDocTypeFilterDTO> {

    private final GraduationDocTypeService service;

    @GetMapping("/by-education-type")
    public List<GraduationDocTypeDTO> getGraduationDocTypesByEducationType(@RequestParam EducationType educationType,
                                                                           @RequestParam(required = false, defaultValue = "false") boolean onlyActive){
        return service.getByEducationType(educationType, onlyActive);
    }

    @Override
    protected NomenclatureServiceBase<Integer, GraduationDocTypeDTO, GraduationDocTypeFilterDTO> getService() {
        return service;
    }
}
