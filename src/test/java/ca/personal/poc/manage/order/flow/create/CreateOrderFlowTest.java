package ca.personal.poc.manage.order.flow.create;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.personal.poc.manage.App;
import ca.personal.poc.manage.customer.dto.CustomerDTO;
import ca.personal.poc.manage.db.DBService;
import ca.personal.poc.manage.order.dto.ItemDTO;
import ca.personal.poc.manage.order.dto.OrderDTO;
import ca.personal.poc.manage.order.model.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class CreateOrderFlowTest {

	@Autowired
	private DBService db;

	@Autowired
	private CreateOrderFlow createOrderFlow;

	private Object[] createInputs() {
		List<ItemDTO> items = new ArrayList<>();
		
		ItemDTO item1 = new ItemDTO();
		item1.setProductID(UUID.fromString("916f424b-08e3-4c58-ad71-6f7d27d1ecbf"));
		item1.setQuantity(1);
		items.add(item1);
		
		ItemDTO item2 = new ItemDTO();
		item2.setProductID(UUID.fromString("4170a34c-4707-45cc-989c-9678aaf63d96"));
		item2.setQuantity(2);
		items.add(item2);

		CustomerDTO customer = new CustomerDTO();
		customer.setId(UUID.fromString("f85321a9-bc00-4f1c-a916-b8639bb90371"));
		
		OrderDTO order = new OrderDTO();
		order.setCustomer(customer);
		order.setItems(items);
		order.setDate(ZonedDateTime.now());
		
		return new Object[] { order, order.getCustomer() };
	}
    
    @Before
    public void initDb() {
    	log.info("Method: initDb()");
    	db.initTables();
    }
	
	@Test
	public void createOrderFlowTest() {
		Order order = createOrderFlow.input(createInputs()).performOutput();
		assertThat(order).isNotNull();
		assertThat(order.getCustomer().getFirstName()).isEqualTo("Orlei");
		assertThat(order.getTotal()).isEqualTo(BigDecimal.valueOf(33.91));
		
		createOrderFlow.dispose();
		
		log.debug("Order: [{}]", order);
	}
}
