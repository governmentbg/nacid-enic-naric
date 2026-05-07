package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfGroupDTO extends IntegerKeyNomenclatureBase {
    private ReferenceDataDTO educationArea;
    public ProfGroupDTO(Integer id) {
        this.id = id;
    }
    public ProfGroupDTO(Integer id, String name, Boolean isActive,ReferenceDataDTO educationArea) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.educationArea = educationArea;
    }
}
