package bg.duosoft.nacid.backoffice.abdocs.service.security;

public interface AbdocsTokenHolderService {

    String selectAccessToken(String username);

    String selectAdminAccessToken();

}
