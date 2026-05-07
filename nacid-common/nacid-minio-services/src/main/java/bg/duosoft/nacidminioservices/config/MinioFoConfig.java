package bg.duosoft.nacidminioservices.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 15:40
 */
@Configuration
@Import(MinioScanConfig.class)
public class MinioFoConfig {

    @Value("${minio.frontoffice.client.endpoint}")
    private String minioEndpoint;

    @Value("${minio.frontoffice.client.username}")
    private String minioUsername;

    @Value("${minio.frontoffice.client.password}")
    private String minioPassword;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(minioEndpoint).credentials(minioUsername, minioPassword).build();
        minioClient.setTimeout(10000, 30000, 30000);
        return minioClient;
    }
}
