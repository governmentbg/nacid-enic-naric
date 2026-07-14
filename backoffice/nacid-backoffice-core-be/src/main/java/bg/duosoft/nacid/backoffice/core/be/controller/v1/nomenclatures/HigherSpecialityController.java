package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.HigherSpecialityService;
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
@Api(tags = Tags.HIGHER_SPECIALITY)
@RequestMapping("/api/v1/higher-specialities")
public class HigherSpecialityController {

    private final HigherSpecialityService higherSpecialityService;

    @GetMapping
    @ApiOperation(value = "Select higher speciality records")
    public List<String> getHigherSpecialities(AutocompleteViewFilterDTO filter) {
        return higherSpecialityService.selectHigherSpecialities(filter);
    }
}
