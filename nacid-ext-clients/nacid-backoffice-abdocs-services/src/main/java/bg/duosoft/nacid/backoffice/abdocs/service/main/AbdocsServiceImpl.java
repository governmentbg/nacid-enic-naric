
package bg.duosoft.nacid.backoffice.abdocs.service.main;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsClient;
import bg.duosoft.nacid.backoffice.abdocs.client.BaseAbdocsClient;
import bg.duosoft.nacid.backoffice.abdocs.service.main.base.CommonAbdocsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsServiceImpl extends CommonAbdocsServiceImpl implements AbdocsService {

    private final AbdocsClient client;

    @Override
    public BaseAbdocsClient getClient() {
        return client;
    }
}
