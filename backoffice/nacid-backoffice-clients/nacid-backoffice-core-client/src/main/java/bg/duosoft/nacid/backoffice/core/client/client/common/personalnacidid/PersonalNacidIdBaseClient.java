package bg.duosoft.nacid.backoffice.core.client.client.common.personalnacidid;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonalNacidIdDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface PersonalNacidIdBaseClient {

    @PostMapping("/back-office")
    public PersonalNacidIdDTO generateForBackOffice();

    @PostMapping("/front-office")
    PersonalNacidIdDTO generate(@RequestParam("userGenerated") String userGenerated);
}
