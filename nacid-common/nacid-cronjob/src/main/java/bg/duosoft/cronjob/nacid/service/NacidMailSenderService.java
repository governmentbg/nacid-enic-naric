package bg.duosoft.cronjob.nacid.service;

import bg.duosoft.cronjob.mail.CronjobMailSenderService;
import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * User: ggeorgiev
 * Date: 05.06.2023
 * Time: 14:01
 */
@Service
@Slf4j
public class NacidMailSenderService  implements CronjobMailSenderService, ApplicationContextAware {
    /***
     * imashe nqkakvi drami s Autowirevaneto na kakyvto i da e bean. Ako mahna implements CronjobMailSenderService, raboteshe, inache ne iskashe da autowireva
     * zatova injectovam applicationContext-a i ot nego si vzemam mail sender service-a !!!!
     */
    private ApplicationContext context;

    public boolean sendEmail(String to, String header, String body) {
        try {
            log.debug("Sending mail to " + to + " header: " + header + " body:" + body);
            context.getBean(MailSenderService.class).sendSimpleMail(to, header, body);
            return true;
        } catch (Exception e) {
            log.error("Error trying to send email to" + to + " header: " + header + " body:" + body, e);
            return false;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
