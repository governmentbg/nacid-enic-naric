package bg.duosoft.email.nacidemailproducer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "bg.duosoft.email.nacidemailproducer.repository",
        entityManagerFactoryRef = "pdbEntityManager",
        transactionManagerRef = "pdbTransactionManager"
)
@EnableJpaAuditing
public class MailDatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
//    @Primary
    public LocalContainerEntityManagerFactoryBean pdbEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(pdbDataSource());
        em.setPackagesToScan("bg.duosoft.email.nacidemailproducer.domain.entity");

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
//    @Primary
    @ConfigurationProperties(prefix = "pdb.datasource")
    public DataSource pdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
//    @Primary
    public PlatformTransactionManager pdbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(pdbEntityManager().getObject());
        return transactionManager;
    }

}