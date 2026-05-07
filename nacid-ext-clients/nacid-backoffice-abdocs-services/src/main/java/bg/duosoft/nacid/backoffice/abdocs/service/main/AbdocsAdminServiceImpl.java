
package bg.duosoft.nacid.backoffice.abdocs.service.main;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsAdminClient;
import bg.duosoft.nacid.backoffice.abdocs.client.BaseAbdocsClient;
import bg.duosoft.nacid.backoffice.abdocs.service.main.base.CommonAbdocsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsAdminServiceImpl extends CommonAbdocsServiceImpl implements AbdocsAdminService {

    private final AbdocsAdminClient client;

    @Override
    public BaseAbdocsClient getClient() {
        return client;
    }
}
