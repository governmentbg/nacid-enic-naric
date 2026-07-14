package bg.duosoft.cronjob.nacid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {"bg.duosoft.email.nacidemailproducer.repository"},
        entityManagerFactoryRef = "nacidCronjobEntityManager",
        transactionManagerRef = "nacidCronjobTransactionManager"
)
public class NacidCronjobDatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean nacidCronjobEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(nacidCronjobDataSource());
        em.setPackagesToScan("bg.duosoft.email.nacidemailproducer.domain");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("pdb.jpa.database-platform"));
        properties.put("hibernate.show_sql", env.getProperty("pdb.hibernate.show_sql"));
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.dynamic-update", true);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "pdb.datasource")
    public DataSource nacidCronjobDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager nacidCronjobTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(nacidCronjobEntityManager().getObject());
        return transactionManager;
    }

}