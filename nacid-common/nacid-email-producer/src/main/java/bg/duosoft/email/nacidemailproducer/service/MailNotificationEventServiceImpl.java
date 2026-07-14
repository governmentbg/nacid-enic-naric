package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotificationEvent;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailNotificationEvent;
import bg.duosoft.email.nacidemailproducer.domain.mapper.EmailNotificationEventMapper;
import bg.duosoft.email.nacidemailproducer.repository.EmailNotificationEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@Transactional(value = "pdbTransactionManager")
@RequiredArgsConstructor
public class MailNotificationEventServiceImpl implements MailNotificationEventService {

    private final EmailNotificationEventRepository repository;
    private final EmailNotificationEventMapper mapper;

    @Override
    public CEmailNotificationEvent findById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }

        EEmailNotificationEvent entity = repository.findById(id).orElse(null);
        return mapper.toCore(entity);
    }
}
