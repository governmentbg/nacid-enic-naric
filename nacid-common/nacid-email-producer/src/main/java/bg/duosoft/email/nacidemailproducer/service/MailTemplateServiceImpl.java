package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemplate;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailTemplate;
import bg.duosoft.email.nacidemailproducer.domain.mapper.EmailTemplateMapper;
import bg.duosoft.email.nacidemailproducer.filter.EmailTemplateFilter;
import bg.duosoft.email.nacidemailproducer.repository.EmailTemplateRepository;
import bg.duosoft.email.nacidemailproducer.repository.custom.EmailTemplateRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(value = "pdbTransactionManager")
@RequiredArgsConstructor
public class MailTemplateServiceImpl implements MailTemplateService {

    private final EmailTemplateMapper templateMapper;
    private final EmailTemplateRepository templateRepository;
    private final EmailTemplateRepositoryCustom templateRepositoryCustom;

    @Override
    public List<CEmailTemplate> selectEmailTemplates(EmailTemplateFilter filter) {
        List<CEmailTemplate> result = templateRepositoryCustom.selectEmailTemplates(filter);
        return result;
    }

    @Override
    public int selectEmailTemplatesCount(EmailTemplateFilter filter) {
        return templateRepositoryCustom.selectEmailTemplatesCount(filter);
    }

    @Override
    public List<CEmailTemplate> selectAllTemplates() {
        return templateMapper.toCoreList(templateRepository.findAll());
    }

    @Override
    public void deleteEmailTemplateById(String id) {
        templateRepository.deleteById(id);
    }

    @Override
    public void saveEmailTemplate(CEmailTemplate emailTemplate) {
        templateRepository.save(templateMapper.toEntity(emailTemplate));
    }

    @Override
    public CEmailTemplate findById(String id) {
        Optional<EEmailTemplate> templateOptional = templateRepository.findById(id);
        return templateOptional.map(templateMapper::toCore).orElse(null);
    }

}
