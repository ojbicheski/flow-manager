/**
 * 
 */
package ca.personal.poc.manage.customer.services;

import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ca.personal.poc.manage.customer.exception.CustomerException;
import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.customer.repository.CustomerRepository;

/**
 * @author Orlei Bicheski
 *
 */
@Service
@Transactional
public class CustomerService {
	
	private final CustomerRepository repository;
	
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public Customer get(UUID id) {
		if (Objects.isNull(id)) {
			throw new CustomerException("Invalid ID");
		}
		return repository.findById(id)
				.orElseThrow(() -> new CustomerException("Customer not found."));
	}

	public boolean checkCredit(Customer customer) {
		// Credit simulation 
		return true;
	}
	
}
