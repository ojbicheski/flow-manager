/**
 * 
 */
package ca.personal.poc.manage.config;

import static ca.personal.poc.manage.config.PostgreSQLProperties.BEAN_NAME_EMBEDDED_POSTGRESQL;
import static com.playtika.test.common.utils.ContainerUtils.configureCommonsAndStart;
import static org.testcontainers.shaded.com.google.common.base.Strings.isNullOrEmpty;

import java.util.LinkedHashMap;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import com.playtika.test.common.spring.DockerPresenceBootstrapConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Slf4j
@Configuration
@ConditionalOnExpression("${embedded.containers.enabled:true}")
@AutoConfigureAfter(DockerPresenceBootstrapConfiguration.class)
@ConditionalOnProperty(name = "embedded.postgresql.enabled", matchIfMissing = true)
@EnableConfigurationProperties(PostgreSQLProperties.class)
public class EmbeddedPostgreSQLBootstrapConfiguration {
	
    @Bean(name = BEAN_NAME_EMBEDDED_POSTGRESQL, destroyMethod = "stop")
    public ConcretePostgreSQLContainer postgresql(
    		ConfigurableEnvironment environment,
            PostgreSQLProperties properties) {
        log.info("Starting postgresql server. Docker image: {}", properties.getDockerImage());

        @SuppressWarnings("resource")
		ConcretePostgreSQLContainer postgresql =
                new ConcretePostgreSQLContainer(DockerImageName.parse(properties.getDockerImage())
                        .asCompatibleSubstituteFor("postgres"))
                        .withUsername(properties.getUser())
                        .withPassword(properties.getPassword())
                        .withDatabaseName(properties.getDatabase())
//                        .withInitScript(properties.getInitScriptPath())
                        .withFileSystemBind("pgdata", "/var/lib/postgresql/data", BindMode.READ_WRITE);

        String startupLogCheckRegex = properties.getStartupLogCheckRegex();
        if (!isNullOrEmpty(startupLogCheckRegex)) {
            postgresql.setWaitStrategy(new LogMessageWaitStrategy().withRegEx(startupLogCheckRegex));
        }

        return registerPostgresqlEnvironment(
        		(ConcretePostgreSQLContainer) configureCommonsAndStart(postgresql, properties, log), 
        		environment, 
        		properties);
    }

    private ConcretePostgreSQLContainer registerPostgresqlEnvironment(
    		ConcretePostgreSQLContainer postgresql,
            ConfigurableEnvironment environment,
            PostgreSQLProperties properties) {
        
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("embedded.postgresql.port", postgresql.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT));
        map.put("embedded.postgresql.host", postgresql.getContainerIpAddress());
        map.put("embedded.postgresql.schema", postgresql.getDatabaseName());
        map.put("embedded.postgresql.user", postgresql.getUsername());
        map.put("embedded.postgresql.password", postgresql.getPassword());

        log.info("Started postgresql server. Connection details: {}, JDBC connection url: jdbc:postgresql://{}:{}/{}", 
                map, 
                postgresql.getContainerIpAddress(), 
                postgresql.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT), 
                postgresql.getDatabaseName());
        log.info("Username: {}, Password: {}", postgresql.getUsername(), postgresql.getPassword());

        environment.getPropertySources().addFirst(new MapPropertySource("embeddedPostgreInfo", map));
        
        return postgresql;
    }

    private static class ConcretePostgreSQLContainer extends PostgreSQLContainer<ConcretePostgreSQLContainer> {
        public ConcretePostgreSQLContainer(DockerImageName dockerImageName) {
            super(dockerImageName);
        }
    }
    
}
