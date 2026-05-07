package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;

import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.SettlementFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SettlementValidator extends BaseNomenclatureValidator<String, SettlementDTO, SettlementFilterDTO> {
}
