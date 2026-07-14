package bg.duosoft.nacid.backoffice.core.client.client.error_log;

import org.springframework.web.bind.annotation.PutMapping;

public interface BaseErrorLogClient {

    @PutMapping({"/resolution/auto"})
    void resolveAllAutomatically();
}
