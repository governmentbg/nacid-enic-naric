package bg.duosoft.nacid.backoffice.abdocs.config.security.interceptop;

import bg.duosoft.nacid.backoffice.abdocs.service.security.AbdocsTokenHolderService;
import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsServiceSecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

@Slf4j
public class LoggedUserAuthInterceptor implements RequestInterceptor {

    @Autowired
    private AbdocsTokenHolderService abdocsTokenHolderService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (AbdocsServiceSecurityUtils.isUserAuthenticated()) {
            String username = AbdocsServiceSecurityUtils.getUsername();
            String accessToken = abdocsTokenHolderService.selectAccessToken(username);
            requestTemplate.removeHeader(HttpHeaders.AUTHORIZATION);
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        }
    }

}
