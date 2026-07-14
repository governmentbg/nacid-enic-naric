package bg.duosoft.email.nacidemailproducer.config;

import bg.duosoft.email.nacidemailproducer.property.EmailProducerPropertyAccess;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {
        "bg.duosoft.email.nacidemailproducer.property",
        "bg.duosoft.email.nacidemailproducer.domain",
        "bg.duosoft.email.nacidemailproducer.repository",
        "bg.duosoft.email.nacidemailproducer.service",
        "bg.duosoft.email.nacidemailproducer.utils",
        "bg.duosoft.email.nacidemailproducer.events"
})
public class RabbitMQConfig {

    private final EmailProducerPropertyAccess propertyAccess;

    @Bean
    @Primary
    @SneakyThrows
    public ConnectionFactory connectionFactory() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(propertyAccess.getMqHost());
        cf.setPort(propertyAccess.getMqPort());
        cf.setVirtualHost(propertyAccess.getMqMailVhost());
        cf.setUsername(propertyAccess.getMqUsername());
        cf.setPassword(propertyAccess.getMqPassword());

        if (propertyAccess.getSslEnabled()) {
            cf.useSslProtocol();
        }

        return cf;
    }

    @Bean
    @Primary
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(connectionFactory());
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

    @Bean
    @Primary
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(cachingConnectionFactory());
    }

    @Bean
    @Primary
    public Queue primaryQueue() {
        return QueueBuilder.durable(propertyAccess.getMqMailQueueName())
                .deadLetterExchange(propertyAccess.getMqMailExchangeName())
                .deadLetterRoutingKey(propertyAccess.getMqMailWaitQueueName())
                .build();
    }

    @Bean
    @Primary
    public Queue waitQueue() {
        return QueueBuilder.durable(propertyAccess.getMqMailWaitQueueName())
                .deadLetterExchange(propertyAccess.getMqMailExchangeName())
                .deadLetterRoutingKey(propertyAccess.getMqMailRouteKey())
                .ttl(propertyAccess.getMqMailWaitQueueTtl())
                .build();
    }

    @Bean
    @Primary
    public Queue parkingLotQueue() {
        return new Queue(propertyAccess.getMqMailParkingLotQueueName());
    }

    @Bean
    @Primary
    public DirectExchange exchange() {
        return new DirectExchange(propertyAccess.getMqMailExchangeName());
    }

    @Bean
    @Primary
    public Binding emailBinding() {
        return BindingBuilder.bind(primaryQueue()).to(exchange()).with(propertyAccess.getMqMailRouteKey());
    }

    @Bean
    @Primary
    public Binding waitBinding() {
        return BindingBuilder.bind(waitQueue()).to(exchange()).with(propertyAccess.getMqMailWaitQueueName());
    }

    @Bean
    @Primary
    public Binding parkingBinding() {
        return BindingBuilder.bind(parkingLotQueue()).to(exchange()).with(propertyAccess.getMqMailParkingLotQueueName());
    }

}
