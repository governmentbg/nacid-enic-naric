package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompetentInstitutionFilterDTO extends BaseNomenclatureFilterDTO<Integer> {
    private String originalName;
    private String countryCode;
    private String city;
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String postCode;
    private String url;

    private String orderBy = NomenclatureSortFields.ID;

}
