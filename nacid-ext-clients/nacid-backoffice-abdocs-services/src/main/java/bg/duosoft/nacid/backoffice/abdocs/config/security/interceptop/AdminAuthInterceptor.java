package bg.duosoft.nacid.backoffice.abdocs.config.security.interceptop;

import bg.duosoft.nacid.backoffice.abdocs.service.security.AbdocsTokenHolderService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

@Slf4j
public class AdminAuthInterceptor implements RequestInterceptor {

    @Autowired
    private AbdocsTokenHolderService abdocsTokenHolderService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = abdocsTokenHolderService.selectAdminAccessToken();
        requestTemplate.removeHeader(HttpHeaders.AUTHORIZATION);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }

}
