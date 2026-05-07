package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemplate;
import bg.duosoft.email.nacidemailproducer.filter.EmailTemplateFilter;

import java.util.List;

public interface MailTemplateService {

    List<CEmailTemplate> selectEmailTemplates(EmailTemplateFilter filter);

    int selectEmailTemplatesCount(EmailTemplateFilter filter);

    List<CEmailTemplate> selectAllTemplates();

    void deleteEmailTemplateById(String id);

    void saveEmailTemplate(CEmailTemplate emailTemplate);

    CEmailTemplate findById(String id);

}
