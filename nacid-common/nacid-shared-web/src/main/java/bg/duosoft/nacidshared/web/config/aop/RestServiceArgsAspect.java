package bg.duosoft.nacidshared.web.config.aop;

import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RestServiceArgsAspect {

    private final JsonUtil jsonUtil;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchMapping() {
    }

    @Around("restController() && (putMapping() || postMapping() || patchMapping())")
    public Object replaceEmptyObjectWithNull(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Method method = null;
        try {
            if (Objects.nonNull(args) && args.length > 0) {
                MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
                if (Objects.nonNull(methodSig)) {
                    method = methodSig.getMethod();
                    if (Objects.nonNull(method)) {
                        for (int i = 0; i < args.length; i++) {
                            if (hasRequestBodyAnnotation(method, i)) {
                                args[i] = jsonUtil.removeEmptyObjects(args[i]);
                                log.debug("[RestServiceArgsAspect] Service arguments have been updated successfully! Controller: {}, Method: {}, Parameter: {}", joinPoint.getTarget().getClass(), method.getName(), method.getParameterTypes()[i]);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("[RestServiceArgsAspect] Cannot process request! Execution of the program will continue with the original service arguments. Controller {}, Method: {}", joinPoint.getTarget().getClass(), Objects.isNull(method) ? "unknown" : method.getName());
            log.error(e.getMessage(), e);
        }

        return joinPoint.proceed(args);
    }

    private static boolean hasRequestBodyAnnotation(Method method, int index) {
        Annotation[] annotations = method.getParameterAnnotations()[index];
        for (Annotation annotation : annotations) {
            if (annotation instanceof RequestBody) {
                return true;
            }
        }
        return false;
    }

}
