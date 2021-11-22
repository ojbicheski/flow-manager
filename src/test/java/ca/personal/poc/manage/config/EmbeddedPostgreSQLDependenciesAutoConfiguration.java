/**
 * 
 */
package ca.personal.poc.manage.config;

import static ca.personal.poc.manage.config.PostgreSQLProperties.BEAN_NAME_EMBEDDED_POSTGRESQL;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.playtika.test.common.spring.DependsOnPostProcessor;

/**
 * @author Orlei Bicheski
 *
 */
@Configuration
@AutoConfigureOrder
@ConditionalOnClass(DataSource.class)
@ConditionalOnExpression("${embedded.containers.enabled:true}")
@ConditionalOnProperty(name = "embedded.postgresql.enabled", matchIfMissing = true)
@AutoConfigureAfter(name = "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration")
public class EmbeddedPostgreSQLDependenciesAutoConfiguration {

    @Bean
    public static BeanFactoryPostProcessor dsPostgreSqlDependencyPostProcessor() {
        return new DependsOnPostProcessor(DataSource.class, new String[]{BEAN_NAME_EMBEDDED_POSTGRESQL});
    }

}
