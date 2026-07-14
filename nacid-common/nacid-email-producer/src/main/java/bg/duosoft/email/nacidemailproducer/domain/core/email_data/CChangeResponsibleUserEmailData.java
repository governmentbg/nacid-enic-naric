package bg.duosoft.email.nacidemailproducer.domain.core.email_data;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.base.CEmailDataParamsConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CChangeResponsibleUserEmailData extends CEmailDataParamsConverter {

    private String targetUsername;
    private String sourceUsername;
    private String appEntryNumber;

}
