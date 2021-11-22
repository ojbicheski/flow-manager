/**
 * 
 */
package ca.personal.poc.manage.db;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Service
@Slf4j
public class DBService {
	
	@Autowired
	private JdbcTemplate jdbc;	

	@Value("classpath:db/postgresql/V1__init.sql")
	private Resource resource;

    public void initTables() {
		try {
			jdbc.execute(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8.name()));
			checkTables();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
    
    private void checkTables() {
    	try {
    		log.info("Tables: {}", 
    				jdbc.queryForObject("SELECT COUNT(1) AS TOTAL "
    						+ "FROM INFORMATION_SCHEMA.TABLES T "
    						+ "WHERE UPPER(T.TABLE_SCHEMA) = 'SALES'", Integer.class));

    		if (log.isDebugEnabled()) {
    			log.debug("Table names: ");
    			jdbc.queryForList("SELECT TABLE_NAME "
    					+ "FROM INFORMATION_SCHEMA.TABLES T "
    					+ "WHERE UPPER(T.TABLE_SCHEMA) = 'SALES' ORDER BY TABLE_NAME", String.class)
    			.forEach(t -> log.debug("-> " + t));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
