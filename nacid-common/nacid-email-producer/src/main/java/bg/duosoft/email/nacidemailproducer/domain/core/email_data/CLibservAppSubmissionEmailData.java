package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.base.CEmailDataParamsConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CLibservAppSubmissionEmailData extends CEmailDataParamsConverter {

    private String username;
    private String userFullName;
    private String appNumber;
    private String appType;

}
