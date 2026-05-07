package bg.duosoft.nacidcoreapi.config;

import bg.duosoft.nacidminioservices.config.MinioFoConfig;
import bg.duosoft.nacidminioservices.config.MinioScanConfig;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 15:40
 */
@Configuration
@Import(MinioFoConfig.class)
public class MinioConfig {

}
