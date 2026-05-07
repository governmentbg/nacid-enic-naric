package bg.duosoft.nacidshared.web.config.cache;

import bg.duosoft.logging.annotation.LogExecutionTime;
import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.logging.configuration.EnableLoggingExtrasConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: ggeorgiev
 * Date: 25.10.2022
 * Time: 15:09
 */
//@Configuration
@Slf4j
@ConditionalOnBean(EnableLoggingExtrasConfiguration.class)
public class ScanBeansWithLogObjectChangeAnnotation  implements ApplicationListener<ContextRefreshedEvent> {

    public void getBeans(ApplicationContext applicationContext) throws BeansException {
        Set<String> services = new TreeSet<>();
        Set<String> operations = new TreeSet<>();
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object obj = applicationContext.getBean(beanName);

            /*
             * As you are using AOP check for AOP proxying. If you are proxying with Spring CGLIB (not via Spring AOP)
             * Use org.springframework.cglib.proxy.Proxy#isProxyClass to detect proxy If you are proxying using JDK
             * Proxy use java.lang.reflect.Proxy#isProxyClass
             */
            Class<?> objClz = obj.getClass();
            if (org.springframework.aop.support.AopUtils.isAopProxy(obj)) {

                objClz = org.springframework.aop.support.AopUtils.getTargetClass(obj);
            }

            for (Method m : objClz.getMethods()) {
                if (m.isAnnotationPresent(LogObjectChange.class)) {
                    LogObjectChange annotation = m.getAnnotation(LogObjectChange.class);
                    //Should give you expected results
                    services.add(StringUtils.hasText(annotation.service()) ? annotation.service() : objClz.getName());
                    operations.add(StringUtils.hasText(annotation.operation()) ? annotation.operation() : m.getName());
                }
            }
        }
        log.debug("Services");
        services.forEach(log::debug);
        log.debug("Operations");
        operations.forEach(log::debug);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() instanceof GenericWebApplicationContext) {
            getBeans(event.getApplicationContext());
        }

    }
}
