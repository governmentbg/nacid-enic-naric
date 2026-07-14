package bg.duosoft.nacid.backoffice.core.be.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "bg.duosoft.nacid.backoffice.core.data.mapper",
        "bg.duosoft.nacidshareddata",
        "bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter"}
)
public class CoreDataConfig {
}