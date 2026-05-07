package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommissionMemberPositionDTO extends StringKeyNomenclatureBase {
    private ReferenceDataDTO applicationStatus;
    public CommissionMemberPositionDTO(String id) {
        this.id = id;
    }
    public CommissionMemberPositionDTO(String id, String name, Boolean isActive, ReferenceDataDTO applicationStatus) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.applicationStatus = applicationStatus;
    }
}
