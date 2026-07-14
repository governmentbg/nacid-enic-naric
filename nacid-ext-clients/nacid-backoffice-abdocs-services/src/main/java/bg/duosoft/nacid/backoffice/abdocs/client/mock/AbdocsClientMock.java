package bg.duosoft.nacid.backoffice.abdocs.client.mock;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsClient;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"dev", "office"})
@Component
@Primary
public class AbdocsClientMock extends BaseAbdocsClientMock implements AbdocsClient {

}
