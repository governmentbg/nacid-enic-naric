package bg.duosoft.nacidshared.web.config;

import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 16.09.2022
 * Time: 14:30
 */
@Aspect
@Component
@Slf4j
public class BaseAccessAspect {
    private enum Operation {
        READ, EDIT, DELETE;
    }
    @Pointcut("target(bg.duosoft.nacidshared.web.controller.BaseAccessController)")
    public void crudController() {}

    @Pointcut("@annotation(org.springframework.security.access.prepost.PreAuthorize)")
    public void preAuthorize() {}

    @Before("crudController() && !(preAuthorize()) && @annotation(org.springframework.web.bind.annotation.GetMapping) ")
    public void insideGetMethod(JoinPoint joinPoint) {
        checkPermissions(joinPoint, Operation.READ);
    }
    @Before("crudController() && !(preAuthorize()) && (@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.PatchMapping))")
    public void insideEditMethod(JoinPoint joinPoint) {
        checkPermissions(joinPoint, Operation.EDIT);
    }
    @Before("crudController() && !(preAuthorize()) && @annotation(org.springframework.web.bind.annotation.DeleteMapping) ")
    public void insideDeleteMethod(JoinPoint joinPoint) {
        checkPermissions(joinPoint, Operation.DELETE);
    }
    @Before("crudController() && !(preAuthorize()) && @annotation(requestMapping)")
    public void insideRequestMappingMethod(JoinPoint joinPoint, RequestMapping requestMapping) {

        Set<RequestMethod> set = Arrays.stream(requestMapping.method()).collect(Collectors.toSet());
        if (set.contains(RequestMethod.GET)) {
            checkPermissions(joinPoint, Operation.READ);
        }
        if (set.contains(RequestMethod.POST) || set.contains(RequestMethod.PUT) || set.contains(RequestMethod.PATCH)) {
            checkPermissions(joinPoint, Operation.EDIT);
        }
        if (set.contains(RequestMethod.DELETE)) {
            checkPermissions(joinPoint, Operation.DELETE);
        }
    }

    protected void checkPermissions(JoinPoint joinPoint, Operation operation) {
        BaseAccessController controller = (BaseAccessController) joinPoint.getTarget();
        String role = switch (operation) {
            case READ -> controller.getAccessRole();
            case EDIT -> controller.getEditRole();
            case DELETE -> controller.getDeleteRole();
        };
        log.debug("Checking permissions. Controller: " + controller.getClass() + " role: " + role);
        BaseAccessController.checkPermissions(role);
    }


}
