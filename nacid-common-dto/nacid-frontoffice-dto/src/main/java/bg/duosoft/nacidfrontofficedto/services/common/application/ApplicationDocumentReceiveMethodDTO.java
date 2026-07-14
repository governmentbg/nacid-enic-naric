package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.address.ReceiverAddressDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.05.2024
 * Time: 11:14
 */
@Data
public class ApplicationDocumentReceiveMethodDTO {

    private DocumentReceiveMethodDTO resultReceive;
    private ReceiverAddressDTO receiverAddress;
}
