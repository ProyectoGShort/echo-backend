package xyz.proyectogshort.echo.shared.infrastructure.hibernate;

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
        entityManagerFactoryRef = "echoEntityManager",
        transactionManagerRef = "echoTransactionManager",
        basePackages = {"xyz.proyectogshort.echo"}
)
public class EchoHibernateConfiguration {

    @Value("${echo.database.name}") String name;
    @Value("${echo.database.schema}") String schema;
    @Value("${echo.database.host}") String host;
    @Value("${echo.database.port}") String port;
    @Value("${echo.database.username}") String username;
    @Value("${echo.database.password}") String password;
    @Value("${echo.database.ssl}") String ssl;

    @Bean("echoDataSource")
    public DataSource echoDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s?sslmode=%s", host, port, name, ssl));
        config.setSchema(schema);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

    @Bean("echoEntityManager")
    public LocalContainerEntityManagerFactoryBean echoEntityManager() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(echoDataSource());
        factory.setPackagesToScan("xyz.proyectogshort.echo");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        factory.setJpaPropertyMap(properties);

        return factory;
    }

    @Bean("echoTransactionManager")
    public PlatformTransactionManager echoTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(echoEntityManager().getObject());
        return transactionManager;
    }
}
