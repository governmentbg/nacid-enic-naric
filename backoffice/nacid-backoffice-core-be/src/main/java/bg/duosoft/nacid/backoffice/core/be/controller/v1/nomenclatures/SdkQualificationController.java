package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SdkQualificationService;
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
@Api(tags = Tags.HIGHER_QUALIFICATION)
@RequestMapping("/api/v1/sdk-qualifications")
public class SdkQualificationController {
    private final SdkQualificationService sdkQualificationService;

    @GetMapping
    @ApiOperation(value = "Select all sdk qualification records")
    public List<String> selectSdkQualifications(AutocompleteViewFilterDTO filter) {
        return sdkQualificationService.selectSdkQualifications(filter);
    }
}
