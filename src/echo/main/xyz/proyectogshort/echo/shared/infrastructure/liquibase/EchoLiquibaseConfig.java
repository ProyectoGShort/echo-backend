package xyz.proyectogshort.echo.shared.infrastructure.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EchoLiquibaseConfig {

    @Value("${echo.liquibase.enable}")
    private boolean enabled;

    @Bean("echoLiquibase")
    public SpringLiquibase liquibase(
            @Qualifier("echoDataSource") DataSource dataSource
    ) {
        if (!enabled) {
            return null;
        }

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:echo/liquibase/master.xml");
        return liquibase;
    }
}
