package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SettlementService;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SettlementDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SettlementFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.json.model.settlement.SettlementAutocompleteModel;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
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
public class SettlementController extends NomenclatureBaseController<String, SettlementDTO, SettlementFilterDTO> {

    private final SettlementService settlementService;

    @Override
    protected NomenclatureServiceBase<String, SettlementDTO, SettlementFilterDTO> getService() {
        return settlementService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(value = "/autocomplete")
    @ApiOperation(value = "Filter settlements for autocomplete")
    public List<SettlementAutocompleteModel> searchForAutocomplete(SettlementFilterDTO filter) {
        List<SettlementAutocompleteModel> result = new ArrayList<>();
        Page<SettlementDTO> settlementDTOPage = searchData(filter);
        if (Objects.nonNull(settlementDTOPage) && !CollectionUtils.isEmpty(settlementDTOPage.getContent())) {
            List<SettlementDTO> content = settlementDTOPage.getContent();
            result = content.stream()
                    .map(s -> SettlementAutocompleteModel.newInstance(s.getId(), s.getName(), s.getFullSettlementName(), s.getSimpleSettlementName(), s.getIsActive()))
                    .toList();
        }
        return result;
    }

}
