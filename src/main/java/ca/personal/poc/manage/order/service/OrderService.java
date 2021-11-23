/**
 * 
 */
package ca.personal.poc.manage.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.order.model.Item;
import ca.personal.poc.manage.order.model.Order;
import ca.personal.poc.manage.order.repository.OrderRepository;

/**
 * @author Orlei Bicheski
 *
 */
@Service
@Transactional
public class OrderService {

	private final OrderRepository repository;
	
	/**
	 * @param repository
	 */
	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public Order create(Customer customer, List<?> items) {
		Order order = new Order();
		order.setCustomer(customer);
		typedList(items, Item.class).forEach(item -> order.addItem(item));
		repository.save(order);
		return order;
	}

	private <T> List<T> typedList(List<?> untypedList, Class<T> itemClass) {
        List<T> list = new ArrayList<>();
        untypedList.forEach(item -> list.add(itemClass.cast(item)));
        return list;
    }

}
