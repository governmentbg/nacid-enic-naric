package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:12
 */
@Data
@NoArgsConstructor
public class DocumentReceiveMethodDTO extends StringKeyNomenclatureBase {
    public DocumentReceiveMethodDTO(String id) {
        this.id = id;
    }

    private Boolean hasDocumentRecipient;
    private Boolean eservicesRequirePaymentReceipt;
    private Integer index;
    private Boolean defaultFlag;
    private ReferenceDataDTO crfCode;

}
