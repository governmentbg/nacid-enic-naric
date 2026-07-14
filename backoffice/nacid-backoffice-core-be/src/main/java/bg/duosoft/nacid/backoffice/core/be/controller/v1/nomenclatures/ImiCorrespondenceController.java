package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ImiCorrespondenceService;
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
@Api(tags = Tags.IMI_CORRESPONDENCE)
@RequestMapping("/api/v1/imi-correspondences")
public class ImiCorrespondenceController {
    private final ImiCorrespondenceService imiCorrespondenceService;

    @GetMapping
    @ApiOperation(value = "Select imi correspondence records")
    public List<String> getImiCorrespondences(AutocompleteViewFilterDTO filter) {
        return imiCorrespondenceService.selectImiCorrespondences(filter);
    }
}
