package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.SettlementService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidcoredata.util.json.model.settlement.SettlementAutocompleteModel;
import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.SettlementFilterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.SETTLEMENTS)
@RequestMapping("/api/v1/settlements")
public class SettlementController extends NomenclatureSearchBaseController<String, SettlementDTO, SettlementFilterDTO> {

    private final SettlementService settlementService;

    @Override
    protected NomenclatureServiceBase<String, SettlementDTO, SettlementFilterDTO> getService() {
        return settlementService;
    }

    @GetMapping(value = "/autocomplete")
    @ApiOperation(value = "Filter settlements for autocomplete")
    public List<SettlementAutocompleteModel> searchForAutocomplete(SettlementFilterDTO filter) {
        List<SettlementAutocompleteModel> result = new ArrayList<>();
        Page<SettlementDTO> settlementDTOPage = searchData(filter);
        if (Objects.nonNull(settlementDTOPage) && !CollectionUtils.isEmpty(settlementDTOPage.getContent())) {
            List<SettlementDTO> content = settlementDTOPage.getContent();
            result = content.stream()
                    .map(s -> {
                        String fullSettlementName = s.getName() + ", " + s.getMunicipalitycode().getName() + ", " + s.getDistrictcode().getName();
                        String selectedOptionValue = s.getDistrict() ? s.getName() : fullSettlementName;
                        return SettlementAutocompleteModel.newInstance(s.getId(), s.getName(), fullSettlementName, selectedOptionValue, s.getIsActive());
                    }).toList();
        }
        return result;
    }

}
