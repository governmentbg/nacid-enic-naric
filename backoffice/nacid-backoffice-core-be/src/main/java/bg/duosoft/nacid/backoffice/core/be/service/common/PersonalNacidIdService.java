package bg.duosoft.nacid.backoffice.core.be.service.common;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonalNacidIdDTO;

public interface PersonalNacidIdService {

    PersonalNacidIdDTO selectByValue(String value);

    PersonalNacidIdDTO generateAndSave(String userGenerated);

}
