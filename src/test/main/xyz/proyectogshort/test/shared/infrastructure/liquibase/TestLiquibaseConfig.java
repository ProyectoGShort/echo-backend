package xyz.proyectogshort.test.shared.infrastructure.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TestLiquibaseConfig {

    @Value("${test.liquibase.enable}")
    private boolean enabled;

    @Bean("testLiquibase")
    public SpringLiquibase liquibase(
            @Qualifier("testDataSource") DataSource dataSource
    ) {
        if (!enabled) {
            return null;
        }

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:test/liquibase/master.xml");
        return liquibase;
    }
}
