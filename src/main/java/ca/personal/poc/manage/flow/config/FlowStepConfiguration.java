/**
 * 
 */
package ca.personal.poc.manage.flow.config;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ca.personal.poc.manage.flow.step.StepFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Configuration
@ComponentScan("ca.personal.poc.manage.order.flow.create.flow")
@Slf4j
public class FlowStepConfiguration {

	@Bean
	public ServiceLocatorFactoryBean taskFactory() {
	    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
	    factoryBean.setServiceLocatorInterface(StepFactory.class);
	    
	    log.info("Service locator factor initiated.");
	    return factoryBean;
	}
}
