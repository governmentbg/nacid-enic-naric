package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureManageBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.NationalUniversityService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidcoredata.util.security.SecurityRole;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_NATIONAL_UNIVERSITY)
@RequestMapping("/api/v1/national-universities")
public class NationalUniversityController extends NomenclatureManageBaseController<String, NationalUniversityDTO, NationalUniversityDataFilterDTO> {
    private final NationalUniversityService service;

    @Override
    protected NomenclatureServiceBase<String, NationalUniversityDTO, NationalUniversityDataFilterDTO> getService() {
        return service;
    }

    @Override
    protected String getEditRole() {
        return SecurityRole.NATIONAL_UNIVERSITY_EDIT;
    }

}
