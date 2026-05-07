package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentReceiveOptionDTO  extends StringKeyNomenclatureBase {
    private String id;
    private String name;
    private Boolean isActive;
    private Boolean documentRecipient;
    private Integer index;
    private DocumentReceiveOptionKindDTO optionKind;
}
