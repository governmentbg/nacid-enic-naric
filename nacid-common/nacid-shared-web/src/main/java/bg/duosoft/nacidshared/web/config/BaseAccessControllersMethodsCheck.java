package bg.duosoft.nacidshared.web.config;

import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 19.09.2022
 * Time: 12:54
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class BaseAccessControllersMethodsCheck  implements ApplicationListener<ContextRefreshedEvent> {
    public void validateControllersWithMethodAnnotations(ApplicationContext applicationContext, Class<? extends Annotation>... annotationClasses) throws BeansException {
        List<String> res = new ArrayList<>();
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
            if (!BaseAccessController.class.isAssignableFrom(objClz)) {
                continue;
            }

            for (Method m : getUsableMethodsArrReversed(objClz, annotationClasses)) {
                if (!Modifier.isPublic(m.getModifiers())) {
                    res.add(objClz.getName() + " -> " + m.getName());
                }

            }
        }
        if (res.size() != 0) {
            throw new RuntimeException("Cannot start the application. The following methods inside classes that extend BaseAccessController should be public !\n " + res.stream().collect(Collectors.joining("\n")));
        }
    }
    public static List<Method> getUsableMethodsArrReversed(Class<?> startClass, Class<? extends Annotation>... annotationClass) {
        List<Method> currentClassMethods = new ArrayList<>();
        for (Method f:startClass.getDeclaredMethods()) {
            for (Class<? extends Annotation> a : annotationClass) {
                if (f.isAnnotationPresent(a)) {
                    currentClassMethods.add(f);
                }
            }
        }
        Collections.reverse(currentClassMethods);
        Class<?> parentClass = startClass.getSuperclass();
        if (parentClass != null) {
            List<Method> parentClassFields = getUsableMethodsArrReversed(parentClass, annotationClass);
            currentClassMethods.addAll(parentClassFields);
        }

        return currentClassMethods;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() instanceof GenericWebApplicationContext) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            validateControllersWithMethodAnnotations(event.getApplicationContext(), GetMapping.class, PutMapping.class, PostMapping.class, DeleteMapping.class, RequestMapping.class);
            stopWatch.stop();
            log.debug("Check controller methods finished. Execution time (seconds): " + stopWatch.getTotalTimeSeconds());
        }

    }
}
