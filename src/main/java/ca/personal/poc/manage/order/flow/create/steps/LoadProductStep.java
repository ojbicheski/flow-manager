/**
 * 
 */
package ca.personal.poc.manage.order.flow.create.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ca.personal.poc.manage.flow.step.FlowContextAwareStep;
import ca.personal.poc.manage.order.dto.ItemDTO;
import ca.personal.poc.manage.order.dto.OrderDTO;
import ca.personal.poc.manage.order.exception.OrderException;
import ca.personal.poc.manage.order.model.Item;
import ca.personal.poc.manage.product.model.Product;
import ca.personal.poc.manage.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Component
@Slf4j
public class LoadProductStep extends FlowContextAwareStep {

	public static final Class<OrderDTO> IN_ORDER_DTO = OrderDTO.class;
	public static final String OUT_PRODUCTS_LOADED = "PRODUCTS_LOADED";
	
	private final ProductService service;
	
	/**
	 * @param service
	 */
	public LoadProductStep(ProductService service) {
		this.service = service;
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStep#validate()
	 */
	@Override
	public void validate() {
		log.info("Validate");

		if (!getContext().containsKey(IN_ORDER_DTO)) {
			throw new OrderException("Order not found into Flow Context.");
		}
	}

	/**
	 * @see ca.personal.poc.manage.order.flow.create.flow.step.FlowStep#perform()
	 */
	@Override
	public void perform() {
		log.info("Perform");
		List<Item> items = new ArrayList<>();
		List<ItemDTO> dto = getContext().get(IN_ORDER_DTO).getItems();
		
		service.findAllById(dto.stream()
					.map(item -> item.getProductID())
					.collect(Collectors.toList()))
			.forEach(product -> newItem(product, dto, items));
		
		
		getContext().put(OUT_PRODUCTS_LOADED, items);
	}

	private List<Item> newItem(Product product, List<ItemDTO> input, List<Item> output) {
		input.stream()
			.filter(dto -> dto.getProductID().equals(product.getId()))
			.findFirst()
			.map(dto -> dto.getQuantity())
			.ifPresent(quantity -> {
				Item newItem = new Item();
				newItem.setProduct(product);
				newItem.setQuantity(quantity);
				output.add(newItem);
			});
		return output;
	}

}
