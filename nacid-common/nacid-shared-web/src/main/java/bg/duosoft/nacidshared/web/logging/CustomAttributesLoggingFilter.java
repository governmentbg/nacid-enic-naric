package bg.duosoft.nacidshared.web.logging;

import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: ggeorgiev
 * Date: 06.07.2023
 * Time: 12:14
 */
@Component
public class CustomAttributesLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("loggedUser", SecurityUtils.getUsername() == null ? "unauthenticated" : SecurityUtils.getUsername());
        MDC.put("remoteIp", ObjectUtils.isEmpty(request.getHeader("X-FORWARDED-FOR")) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR"));
        filterChain.doFilter(request, response);
    }
}
