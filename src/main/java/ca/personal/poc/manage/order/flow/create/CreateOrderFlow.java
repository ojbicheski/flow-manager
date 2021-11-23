/**
 * 
 */
package ca.personal.poc.manage.order.flow.create;

import ca.personal.poc.manage.flow.Flow;
import ca.personal.poc.manage.order.dto.OrderDTO;
import ca.personal.poc.manage.order.exception.OrderException;
import ca.personal.poc.manage.order.flow.create.steps.ConfirmOrderStep;
import ca.personal.poc.manage.order.model.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Slf4j
public class CreateOrderFlow extends Flow<Order> {
	
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

		if (!getContext().containsKey(OrderDTO.class)) {
			throw new OrderException("Order not found into Flow Context.");
		}
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStepOutput#performOutput()
	 */
	@Override
	public Order performOutput() {
		super.perform();
		log.info("Perform Output");
		return getContext().get(ConfirmOrderStep.OUT_ORDER);
	}

}
