package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.base.BaseAutocompleteDTO;
import lombok.Builder;
import lombok.Data;

@Data
public class UniversityAutocompleteDTO extends BaseAutocompleteDTO<Integer> {
    private String nameEn;
    private String country;
    private String city;

    @Builder(builderMethodName = "universityBuilder")
    public UniversityAutocompleteDTO(Integer id, String name, Boolean isActive, String nameEn, String country, String city) {
        super(id, name, isActive);
        this.nameEn = nameEn;
        this.country = country;
        this.city = city;
    }
}