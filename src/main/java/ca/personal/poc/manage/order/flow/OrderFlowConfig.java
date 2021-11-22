/**
 * 
 */
package ca.personal.poc.manage.order.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.personal.poc.manage.customer.flow.check.credit.CreditCustomerFlow;
import ca.personal.poc.manage.flow.Flow;
import ca.personal.poc.manage.flow.step.StepFactory;
import ca.personal.poc.manage.order.flow.create.CreateOrderFlow;
import ca.personal.poc.manage.order.flow.create.steps.ConfirmOrderStep;
import ca.personal.poc.manage.order.flow.create.steps.LoadProductStep;

/**
 * @author Orlei Bicheski
 *
 */
@Configuration
public class OrderFlowConfig {

	@Autowired
	private StepFactory factory;
	
	@Bean
	public CreateOrderFlow createOrderFlow() {
		return Flow.builder(CreateOrderFlow.class)
				.withManager(factory)
				.withStep(CreditCustomerFlow.class)
				.withStep(LoadProductStep.class)
				.withStep(ConfirmOrderStep.class)
			.build();
	}
}
