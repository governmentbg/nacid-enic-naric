package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.base.BaseAutocompleteDTO;
import lombok.Builder;
import lombok.Data;

@Data
public class LegalPersonAutocompleteDTO extends BaseAutocompleteDTO<Integer> {
    private String eik;

    @Builder(builderMethodName = "legalPersonBuilder")
    public LegalPersonAutocompleteDTO(Integer id, String name, Boolean isActive, String eik) {
        super(id, name, isActive);
        this.eik = eik;
    }
}