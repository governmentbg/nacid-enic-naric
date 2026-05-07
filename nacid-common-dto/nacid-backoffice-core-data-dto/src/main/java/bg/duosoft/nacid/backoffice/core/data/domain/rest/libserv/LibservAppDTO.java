package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibservAppDTO implements Serializable {

    private ApplicationDTO application;
    private String applicantTitleBefore;
    private String applicantTitleAfter;
    private String multipleApplicationId;

}
