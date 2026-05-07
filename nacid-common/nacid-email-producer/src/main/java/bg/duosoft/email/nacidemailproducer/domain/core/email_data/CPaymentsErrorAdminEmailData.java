package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.base.CEmailDataParamsConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.05.2023
 * Time: 12:22
 */
@Getter
@Setter
@Builder
public class CPaymentsErrorAdminEmailData extends CEmailDataParamsConverter {

    private String errorMessage;
    private String stackTrace;
}
