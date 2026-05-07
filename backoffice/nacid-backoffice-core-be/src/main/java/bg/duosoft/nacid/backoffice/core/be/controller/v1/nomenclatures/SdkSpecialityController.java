package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SdkSpecialityService;
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
@Api(tags = Tags.SDK_SPECIALITY)
@RequestMapping("/api/v1/sdk-specialities")
public class SdkSpecialityController {

    private final SdkSpecialityService sdkSpecialityService;

    @GetMapping
    @ApiOperation(value = "Select sdk speciality records")
    public List<String> getSdkSpecialities(AutocompleteViewFilterDTO filter) {
        return sdkSpecialityService.selectSdkSpecialities(filter);
    }
}
