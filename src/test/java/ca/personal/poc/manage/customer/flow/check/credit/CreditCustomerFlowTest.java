package ca.personal.poc.manage.customer.flow.check.credit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.personal.poc.manage.App;
import ca.personal.poc.manage.customer.dto.CustomerDTO;
import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.db.DBService;
import lombok.extern.slf4j.Slf4j;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class CreditCustomerFlowTest {

	@Autowired
	private DBService db;

	@Autowired
	private CreditCustomerFlow creditCustomerFlow;

	private Object[] createInputs() {
		CustomerDTO customer = new CustomerDTO();
		customer.setId(UUID.fromString("f85321a9-bc00-4f1c-a916-b8639bb90371"));
		
		return new Object[] { customer };
	}
    
    @Before
    public void initDb() {
    	log.info("Method: initDb()");
    	db.initTables();
    }
	
	@Test
	public void creditCustomerFlowTest() {
		Customer customer = creditCustomerFlow.input(createInputs()).performOutput();
		assertThat(customer).isNotNull();
		assertThat(customer.getFirstName()).isEqualTo("Orlei");
		creditCustomerFlow.dispose();
		
		log.debug("Customer: [{}]", customer);
	}
}
