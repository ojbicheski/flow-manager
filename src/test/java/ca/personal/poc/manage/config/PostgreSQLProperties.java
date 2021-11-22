/**
 * 
 */
package ca.personal.poc.manage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.playtika.test.common.properties.CommonContainerProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Orlei Bicheski
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("embedded.postgresql")
public class PostgreSQLProperties extends CommonContainerProperties {

	static final String BEAN_NAME_EMBEDDED_POSTGRESQL = "embeddedPostgreSql";
    String dockerImage = "postgres:12-alpine";

    String user = "postgres";
    String password = "letmein";
    String database = "rules_engine";
    String startupLogCheckRegex = ".*database system is ready to accept connections.*";
    String initScriptPath = "db/postgresql/V1__init.sql";

}
