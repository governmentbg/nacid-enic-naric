package bg.duosoft.email.nacidemailproducer.repository.custom;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemplate;
import bg.duosoft.email.nacidemailproducer.filter.EmailTemplateFilter;

import java.util.List;

public interface EmailTemplateRepositoryCustom {

    List<CEmailTemplate> selectEmailTemplates(EmailTemplateFilter filter);

    int selectEmailTemplatesCount(EmailTemplateFilter filter);

}
