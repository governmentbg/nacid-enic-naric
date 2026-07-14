package bg.duosoft.nacid.backoffice.rudi.be.config.services;

import bg.duosoft.nacid.payments.client.config.PaymentsFeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PaymentsFeignClientConfig.class)
public class PaymentsClientConfig {

}
