package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.base.CEmailDataParamsConverter;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CApplicationSubmittedEmailData extends CEmailDataParamsConverter {

    private String email;
    private String fullName;
    private String entryNumber;
    private String entryDate;
}
