package bg.duosoft.nacidkeycloakservices.config;

import bg.duosoft.nacidkeycloakservices.properties.KeycloakServicesPropertyAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "bg.duosoft.nacidkeycloakservices.repository",
        entityManagerFactoryRef = "keycloakEntityManager",
        transactionManagerRef = "keycloakTransactionManager"
)
@ComponentScan(basePackages = {
        "bg.duosoft.nacidkeycloakservices",
        "bg.duosoft.nacidkeycloakservices.model",
        "bg.duosoft.nacidkeycloakservices.repository",
        "bg.duosoft.nacidkeycloakservices.service",
})
@RequiredArgsConstructor
public class KeycloakDatabaseConfig {

    private final KeycloakServicesPropertyAccess propertyAccess;

    @Bean
    public LocalContainerEntityManagerFactoryBean keycloakEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(keycloakDataSource());
        em.setPackagesToScan("bg.duosoft.nacidkeycloakservices");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", propertyAccess.getKcHibernateDialect());
        properties.put("hibernate.show_sql", propertyAccess.getKcHibernateShowSql());
        properties.put("hibernate.dynamic-update", propertyAccess.getKcHibernateDynamicUpdate());
        properties.put("hibernate.format_sql", propertyAccess.getKcHibernateFormatSql());
        properties.put("javax.persistence.validation.mode", "none");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "keycloak.datasource")
    public DataSource keycloakDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager keycloakTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(keycloakEntityManager().getObject());
        return transactionManager;
    }
}