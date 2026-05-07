package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemporaryKey;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailTemporaryKey;
import bg.duosoft.email.nacidemailproducer.domain.mapper.EmailTemporaryKeyMapper;
import bg.duosoft.email.nacidemailproducer.repository.EmailTemporaryKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@Transactional(value = "pdbTransactionManager")
@RequiredArgsConstructor
public class EmailTemporaryKeyServiceImpl implements EmailTemporaryKeyService {

    private final EmailTemporaryKeyRepository keyRepository;
    private final EmailTemporaryKeyMapper keyMapper;

    @Override
    public CEmailTemporaryKey saveKey(CEmailTemporaryKey key) {
        EEmailTemporaryKey eKey = keyMapper.toEntity(key);
        if (Objects.isNull(eKey)) {
            return null;
        }
        return keyMapper.toCore(keyRepository.save(eKey));
    }

    @Override
    public CEmailTemporaryKey getKey(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        return keyMapper.toCore(keyRepository.findByKey(key));
    }

    @Override
    public CEmailTemporaryKey useKey(String key) {
        CEmailTemporaryKey oKey = getKey(key);
        if (Objects.nonNull(oKey)) {
            oKey.setUsedOnDate(new Date());
            return saveKey(oKey);
        }
        return null;
    }
}
