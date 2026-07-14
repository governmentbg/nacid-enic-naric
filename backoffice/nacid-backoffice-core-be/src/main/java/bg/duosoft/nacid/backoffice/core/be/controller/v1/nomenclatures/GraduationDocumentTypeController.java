package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.GraduationDocumentTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdNameDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.GraduationDocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_GRADUATION_DOCUMENT_TYPE)
@RequestMapping("/api/v1/graduation-document-type")
public class GraduationDocumentTypeController extends NomenclatureBaseController<Integer, GraduationDocumentTypeDTO, GraduationDocumentTypeFilterDTO> {
    private final GraduationDocumentTypeService service;

    @Override
    protected GraduationDocumentTypeService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(value = "/select-by-country-and-education-type")
    @ApiOperation(value = "Select GDTs by country and education type")
    public List<IntegerIdNameDTO> selectByCountryAndEducation(String countryCode, String educationType) {
        return mapToViewDtoList(service.selectByCountryAndEducation(countryCode, educationType));
    }

    @GetMapping(value = "/all-by-country/{id}")
    @ApiOperation(value = "Select all GraduationDocumentTypes by country")
    public List<GraduationDocumentTypeDTO> selectAllByCountry(@PathVariable("id") String countryCode) {
        return service.selectByCountry(countryCode);
    }
    @GetMapping(value = "/select-by-country/{id}")
    @ApiOperation(value = "Select GDTs by country")
    public List<IntegerIdNameDTO> selectByCountry(@PathVariable("id") String countryCode) {
        return mapToViewDtoList(service.selectByCountry(countryCode));
    }

    private List<IntegerIdNameDTO> mapToViewDtoList(List<GraduationDocumentTypeDTO> graduationDocumentTypes) {
        List<IntegerIdNameDTO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(graduationDocumentTypes)) {
            result = graduationDocumentTypes.stream()
                    .map(s -> new IntegerIdNameDTO(s.getId(), s.getName()))
                    .toList();
        }
        return result;
    }


}
