package bg.duosoft.nacid.backoffice.core.be.config;

import bg.duosoft.nacidminioservices.config.MinioBoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 15:40
 */
@Configuration
@Import(MinioBoConfig.class)
public class MinioConfig {

}
