package bg.duosoft.nacid.backoffice.abdocs.service.login;


import bg.duosoft.nacid.backoffice.abdocs.domain.response.SecurityToken;

public interface AbdocsLoginService {

    SecurityToken selectToken(String username);

}
