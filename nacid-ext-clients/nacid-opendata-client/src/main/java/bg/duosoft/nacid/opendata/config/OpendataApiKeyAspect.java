package bg.duosoft.nacid.opendata.config;

import bg.duosoft.nacid.opendata.dto.OpenDataRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: ggeorgiev
 * Date: 08.01.2024
 * Time: 17:38
 */
@Aspect
@Component
public class OpendataApiKeyAspect {
    @Value("${opendata.api-key}")
    private String apiKey;
    @Pointcut("target(bg.duosoft.nacid.opendata.client.OpendataClient)")
    public void opendataClient() {}

    @Around("opendataClient()")
    public Object insideOpendataClientMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object o : args ) {
            if (o instanceof OpenDataRequest r) {
                r.setApiKey(apiKey);
            }
        }
        return joinPoint.proceed(args);
    }

}
