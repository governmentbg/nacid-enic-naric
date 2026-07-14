package bg.duosoft.nacid.backoffice.rudi.be.config;

import bg.duosoft.email.nacidemailproducer.config.MailDatabaseConfig;
import bg.duosoft.email.nacidemailproducer.config.RabbitMQConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RabbitMQConfig.class, MailDatabaseConfig.class})
public class MailProducerConfig {
}
