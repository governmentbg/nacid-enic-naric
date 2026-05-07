package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveOptionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveOptionKindDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.05.2024
 * Time: 11:14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationDocumentReceiveOptionDTO implements Serializable {
    private Integer id;
    private DocumentReceiveOptionDTO documentReceiveOption;
    private AddressDTO documentRecipientAddress;
    private DocumentReceiveOptionKindDTO optionKind;
}
