package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.NationalQualificationsFrameworkService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.NationalQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.NationalQualificationFrameworkFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_NATIONAL_QUALIFICATIONS_FRAMEWORK)
@RequestMapping("/api/v1/national-qualifications-framework")
public class NationalQualificationsFrameworkController extends NomenclatureBaseController<Integer, NationalQualificationFrameworkDTO, NationalQualificationFrameworkFilterDTO> {

    private final NationalQualificationsFrameworkService service;

    @Override
    protected NationalQualificationsFrameworkService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(value = "/select-by-country")
    @ApiOperation(value = "Select records by country code")
    public List<NationalQualificationFrameworkDTO> selectByCountry(String countryCode) {
        if (StringUtils.hasText(countryCode)) {
            List<NationalQualificationFrameworkDTO> nationalQualificationFrameworkDTOS = service.selectByCountryAndEduLevelId(countryCode);
            return nationalQualificationFrameworkDTOS;
        } else
            return null;
    }
}
