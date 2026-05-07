package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.OriginalEduLevelsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.ORIGINAL_EDU_LEVEL)
@RequestMapping("/api/v1/original-edu-levels")
public class OriginalEduLevelController {
    private final OriginalEduLevelsService originalEduLevelsService;

    @GetMapping
    @ApiOperation(value = "Autocomplete original edu levels")
    public List<String> autocompleteOriginalEduLevels(AutocompleteViewFilterDTO filter) {
        return originalEduLevelsService.selectOriginalEduLevels(filter);
    }

    @GetMapping("/translations")
    @ApiOperation(value = "Autocomplete original edu level translations")
    public List<String> autocompleteOriginalEduLevelTranslations(AutocompleteViewFilterDTO filter) {
        return originalEduLevelsService.selectOriginalEduLevelTranslations(filter);
    }
}
