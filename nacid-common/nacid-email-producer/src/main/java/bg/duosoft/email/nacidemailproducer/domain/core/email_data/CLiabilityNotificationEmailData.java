package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CLiabilityNotificationEmailData {
    private String targetUserName;
    private String foReferenceNumber;
    private String amount;
}
