package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;


import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SecondaryProfessionalQualificationService;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionalQualificationFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
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
@Api(tags = Tags.NOM_SECONDARY_PROFESSIONAL_QUALIFICATION)
@RequestMapping("/api/v1/secondary-professional-qualifications")
public class SecondaryProfessionalQualificationController extends NomenclatureBaseController<Integer, SecondaryProfessionalQualificationDTO, SecondaryProfessionalQualificationFilterDTO> {

    private final SecondaryProfessionalQualificationService service;

    @Override
    protected NomenclatureServiceBase<Integer, SecondaryProfessionalQualificationDTO, SecondaryProfessionalQualificationFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping("/name/search")
    @ApiOperation(value = "Select secondary professional qualification name records")
    public List<String> selectSecondaryProfQualificationNames(SecondaryProfessionalQualificationFilterDTO filter) {
        return service.selectSecondaryProfQualificationNames(filter);
    }
}
