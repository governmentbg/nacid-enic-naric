package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:23
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CivilIdTypeDTO extends StringKeyNomenclatureBase {
    private ReferenceDataDTO legalType;
    public CivilIdTypeDTO(String id) {
        this.id = id;
    }
    public CivilIdTypeDTO(String id, String name, Boolean isActive, ReferenceDataDTO legalType) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.legalType = legalType;
    }
}
