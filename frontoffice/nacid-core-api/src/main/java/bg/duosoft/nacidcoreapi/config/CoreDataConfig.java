package bg.duosoft.nacidcoreapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"bg.duosoft.nacidcoredata.mapper", "bg.duosoft.nacidshareddata.validation","bg.duosoft.nacidcoredata.validation", "bg.duosoft.nacidshareddata.property", "bg.duosoft.nacidcoredata.property"})
public class CoreDataConfig {
}
