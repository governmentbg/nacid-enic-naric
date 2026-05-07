package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.base.CEmailDataParamsConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA. User: Raya Date: 03.10.2024 Time: 17:45
 */
@Getter
@Setter
@Builder
public class CARUniRegisterUpdateEmailData extends CEmailDataParamsConverter {

    private String operationType;
    private String acadrecUniEntryRequestId;
    private String acadrecUniId;
    private String user;
}
