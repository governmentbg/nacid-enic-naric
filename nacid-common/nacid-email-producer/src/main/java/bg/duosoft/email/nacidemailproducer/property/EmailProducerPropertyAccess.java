package bg.duosoft.email.nacidemailproducer.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EmailProducerPropertyAccess {

    //Keys timeout
    @Value("${keys.timeout.account-activation:#{15}}")
    private Integer keyTimeoutAccountActivation;

    @Value("${keys.timeout.change-email:#{15}}")
    private Integer keyTimeoutChangeEmail;

    @Value("${keys.timeout.change-username:#{15}}")
    private Integer keyTimeoutChangeUsername;

    @Value("${keys.timeout.reset-password:#{15}}")
    private Integer keyTimeoutResetPassword;

    //Rabbit MQ
    @Value("${rabbitMQ.host}")
    private String mqHost;

    @Value("${rabbitMQ.port}")
    private Integer mqPort;

    @Value("${rabbitMQ.username}")
    private String mqUsername;

    @Value("${rabbitMQ.password}")
    private String mqPassword;

    @Value("${rabbitMQ.email.vhost}")
    private String mqMailVhost;

    @Value("${rabbitMQ.email.queue.name}")
    private String mqMailQueueName;

    @Value("${rabbitMQ.email.exchange.name}")
    private String mqMailExchangeName;

    @Value("${rabbitMQ.email.routekey}")
    private String mqMailRouteKey;

    @Value("${rabbitMQ.email.wait-queue.name}")
    private String mqMailWaitQueueName;

    @Value("${rabbitMQ.email.wait-queue.ttl}")
    private Integer mqMailWaitQueueTtl;

    @Value("${rabbitMQ.email.parking-lot-queue.name}")
    private String mqMailParkingLotQueueName;

    @Value("${rabbitMQ.ssl-enabled}")
    private Boolean sslEnabled;

}
