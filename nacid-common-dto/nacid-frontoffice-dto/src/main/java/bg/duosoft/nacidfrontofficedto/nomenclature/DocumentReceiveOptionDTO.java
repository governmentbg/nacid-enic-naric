package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentReceiveOptionDTO extends NomenclatureBaseImpl<String> {
    private String id;
    private String name;
    private Boolean isActive;
    private Boolean documentRecipient;
    private Integer index;
    private DocumentReceiveOptionKindDTO optionKind;
}
