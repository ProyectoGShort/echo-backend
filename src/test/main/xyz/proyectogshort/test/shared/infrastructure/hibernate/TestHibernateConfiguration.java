package xyz.proyectogshort.test.shared.infrastructure.hibernate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
        entityManagerFactoryRef = "testEntityManager",
        transactionManagerRef = "testTransactionManager",
        basePackages = {"xyz.proyectogshort.test"}
)
public class TestHibernateConfiguration {

    @Value("${test.database.name}") String name;
    @Value("${test.database.schema}") String schema;
    @Value("${test.database.host}") String host;
    @Value("${test.database.port}") String port;
    @Value("${test.database.username}") String username;
    @Value("${test.database.password}") String password;
    @Value("${test.database.ssl}") String ssl;

    @Bean("testDataSource")
    public DataSource testDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s?ssl=%s", host, port, name, ssl));
        config.setSchema(schema);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

    @Bean("testEntityManager")
    public LocalContainerEntityManagerFactoryBean testEntityManager() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(testDataSource());
        factory.setPackagesToScan("xyz.proyectogshort.test");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        factory.setJpaPropertyMap(properties);

        return factory;
    }

    @Bean("testTransactionManager")
    public PlatformTransactionManager testTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(testEntityManager().getObject());
        return transactionManager;
    }
}
