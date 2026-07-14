package bg.duosoft.email.nacidemailproducer.repository;


import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailTemporaryKey;

public interface EmailTemporaryKeyRepository extends BaseRepository<EEmailTemporaryKey, Integer> {

    EEmailTemporaryKey findByKey(String key);

}
