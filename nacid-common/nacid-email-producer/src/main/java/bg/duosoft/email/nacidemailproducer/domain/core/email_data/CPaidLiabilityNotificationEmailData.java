package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CPaidLiabilityNotificationEmailData {
    private String targetUserName;
    private String boReferenceNumber;
}
