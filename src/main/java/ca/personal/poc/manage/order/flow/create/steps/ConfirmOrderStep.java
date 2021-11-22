/**
 * 
 */
package ca.personal.poc.manage.order.flow.create.steps;

import java.util.List;

import org.springframework.stereotype.Component;

import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.flow.step.FlowContextAwareStep;
import ca.personal.poc.manage.order.exception.OrderException;
import ca.personal.poc.manage.order.model.Order;
import ca.personal.poc.manage.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Component
@Slf4j
public class ConfirmOrderStep extends FlowContextAwareStep {

	public static final Class<Customer> IN_CUSTOMER = Customer.class;
	public static final String IN_CREDIT = "CREDIT";
	public static final String IN_PRODUCTS_LOADED = "PRODUCTS_LOADED";

	public static final Class<Order> OUT_ORDER = Order.class;
	
	public final OrderService service;
	
	/**
	 * @param service
	 */
	public ConfirmOrderStep(OrderService service) {
		this.service = service;
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
		
		if (!getContext().containsKey(IN_CREDIT)) {
			throw new OrderException("Credit Check not found into Flow Context.");
		} else if (!getContext().get(IN_CREDIT, Boolean.class)) {
			throw new OrderException("Customer credit was not approved");
		}
		
		if (!getContext().containsKey(IN_PRODUCTS_LOADED)) {
			throw new OrderException("Product list not found into Flow Context.");
		}
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStep#perform()
	 */
	@Override
	public void perform() {
		log.info("Perform");

		getContext().put(service.create(
				getContext().get(IN_CUSTOMER), 
				getContext().get(IN_PRODUCTS_LOADED, List.class)));
	}

}
