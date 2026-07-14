package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;


import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SecondarySpecialityService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BaseNomenclatureDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondarySpecialityFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_SECONDARY_SPECIALITIES)
@RequestMapping("/api/v1/secondary-specialities")
public class SecondarySpecialityController extends NomenclatureBaseController<Integer, SecondarySpecialityDTO, SecondarySpecialityFilterDTO> {

    private final SecondarySpecialityService service;

    @Override
    protected NomenclatureServiceBase<Integer, SecondarySpecialityDTO, SecondarySpecialityFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }


    @GetMapping("/by-professional-qualification")
    @ApiOperation(value = "Select by professional qualification")
    public List<BaseNomenclatureDTO> selectByProfessionalQualification(@RequestParam("profQualificationId") Integer profQualificationId, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        List<SecondarySpecialityDTO> secondarySpecialityDTOs = service.selectByProfessionalQualification(profQualificationId, onlyActive);
        return secondarySpecialityDTOs.stream()
                .map(x -> BaseNomenclatureDTO.newInstance(
                        x.getId().toString(),
                        Objects.nonNull(x.getQualificationDegree()) ? x.getName() + " (" + x.getQualificationDegree().getName() + ")" : x.getName(),
                        Objects.nonNull(x.getQualificationDegree()) ? x.getName() + " (" + x.getQualificationDegree().getName() + ")" : x.getName(),
                        x.getIsActive(),
                        x.getId()))
                .collect(Collectors.toList());
    }
}
