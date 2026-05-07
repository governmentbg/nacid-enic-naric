package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentReceiveMethodDTO extends NomenclatureBaseImpl<String> {
    private String id;
    private String name;
    private Boolean isActive;
    private Boolean documentRecipient;
    private Boolean eservicesRequirePaymentReceipt;
    private Boolean defaultValue;
    private Integer index;
    private String certificateReceiveFormCode;
}
