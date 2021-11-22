package ca.personal.poc.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(value = {
	"ca.personal.poc.manage.customer.repository",
	"ca.personal.poc.manage.order.repository",
	"ca.personal.poc.manage.product.repository"
})
//@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {}
