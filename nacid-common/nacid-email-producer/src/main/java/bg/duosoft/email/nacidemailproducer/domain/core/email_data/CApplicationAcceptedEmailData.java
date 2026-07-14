package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.base.CEmailDataParamsConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CApplicationAcceptedEmailData extends CEmailDataParamsConverter {

    private String email;
    private String fullName;
    private String entryNumber;
    private String entryDate;
    private String accessCode;
}
