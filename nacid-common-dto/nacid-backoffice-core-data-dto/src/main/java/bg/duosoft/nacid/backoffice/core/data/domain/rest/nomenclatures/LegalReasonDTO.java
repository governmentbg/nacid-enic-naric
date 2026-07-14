package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class LegalReasonDTO extends IntegerKeyNomenclatureBase {
    private String ordinanceArticle;
    private String regulationArticle;
    private String regulationText;
    private ReferenceDataDTO applicationStatus;
    private List<CfgLegalReasonToAppTypeDTO> configs;
    public LegalReasonDTO(Integer id) {
        this.id = id;
    }
}
