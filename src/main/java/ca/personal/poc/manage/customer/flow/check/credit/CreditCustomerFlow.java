/**
 * 
 */
package ca.personal.poc.manage.customer.flow.check.credit;

import ca.personal.poc.manage.customer.dto.CustomerDTO;
import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.flow.Flow;
import ca.personal.poc.manage.order.exception.OrderException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Slf4j
public class CreditCustomerFlow extends Flow<Customer> {
	
	public static final Class<CustomerDTO> IN_CUSTOMER = CustomerDTO.class;
	public static final Class<Customer> OUT_CUSTOMER = Customer.class;

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.SkippableStep#isSkip()
	 */
	@Override
	public boolean isSkip() {
		log.info("Skip");
		return false;
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStep#validate()
	 */
	@Override
	public void validate() {
		log.info("Validate");

		if (!getContext().containsKey(IN_CUSTOMER)) {
			throw new OrderException("Customer not found into Flow Context.");
		}
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStepOutput#performOutput()
	 */
	@Override
	public Customer performOutput() {
		log.info("Perform");
		super.perform();
		
		return getContext().get(OUT_CUSTOMER);
	}

}
