package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SchoolAgeRangeService;
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
@Api(tags = Tags.SCHOOL_AGE_RANGE)
@RequestMapping("/api/v1/school-age-ranges")
public class SchoolAgeRangeController {
    private final SchoolAgeRangeService schoolAgeRangeService;

    @GetMapping
    @ApiOperation(value = "Select all school age range records")
    public List<String> selectSchoolGrades(AutocompleteViewFilterDTO filter) {
        return schoolAgeRangeService.selectSchoolAgeRanges(filter);
    }
}
