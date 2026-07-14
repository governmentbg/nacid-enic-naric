package bg.duosoft.nacidservicesbe.config;

import bg.duosoft.email.nacidemailproducer.config.RabbitMQConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RabbitMQConfig.class)
public class MailProducerConfig {
}
