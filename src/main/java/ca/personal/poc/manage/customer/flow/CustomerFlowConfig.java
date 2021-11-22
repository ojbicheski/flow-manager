/**
 * 
 */
package ca.personal.poc.manage.customer.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.personal.poc.manage.customer.flow.check.credit.CreditCustomerFlow;
import ca.personal.poc.manage.customer.flow.check.credit.steps.CheckCustomerStep;
import ca.personal.poc.manage.customer.flow.check.credit.steps.ValidateCustomerCreditStep;
import ca.personal.poc.manage.flow.Flow;
import ca.personal.poc.manage.flow.step.StepFactory;

/**
 * @author Orlei Bicheski
 *
 */
@Configuration
public class CustomerFlowConfig {

	@Autowired
	private StepFactory factory;
	
	@Bean
	public CreditCustomerFlow creditCustomerFlow() {
		return Flow.builder(CreditCustomerFlow.class)
				.withManager(factory)
				.withStep(CheckCustomerStep.class)
				.withStep(ValidateCustomerCreditStep.class)
			.build();
	}
}
