package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemporaryKey;

public interface EmailTemporaryKeyService {

    CEmailTemporaryKey saveKey(CEmailTemporaryKey key);

    CEmailTemporaryKey getKey(String key);

    CEmailTemporaryKey useKey(String key);

}
