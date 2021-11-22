/**
 * 
 */
package ca.personal.poc.manage.customer.flow.check.credit.steps;

import org.springframework.stereotype.Component;

import ca.personal.poc.manage.customer.exception.CustomerException;
import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.customer.services.CustomerService;
import ca.personal.poc.manage.flow.step.FlowContextAwareStep;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Component
@Slf4j
public class ValidateCustomerCreditStep extends FlowContextAwareStep {
	
	public static final Class<Customer> IN_CUSTOMER = Customer.class;
	public static final String OUT_CREDIT = "CREDIT";

	private final CustomerService service;
	
	/**
	 * @param service
	 */
	public ValidateCustomerCreditStep(CustomerService service) {
		this.service = service;
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStep#validate()
	 */
	@Override
	public void validate() {
		log.info("Validate");
		if (!getContext().containsKey(IN_CUSTOMER)) {
			throw new CustomerException("Customer not found into Flow Context.");
		}
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStep#perform()
	 */
	@Override
	public void perform() {
		log.info("Perform");
		getContext().put(OUT_CREDIT,
				service.checkCredit(getContext().get(IN_CUSTOMER)));

	}

}
