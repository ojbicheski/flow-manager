/**
 * 
 */
package ca.personal.poc.manage.customer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.personal.poc.manage.customer.model.Customer;

/** 
 * Spring Data repository for the Customer(tb_customer) entity. 
 * 
 * @author Orlei Bicheski
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
