package ca.personal.poc.manage.customer.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Orlei Bicheski
 *
 */
@Entity
@Table(schema = "sales", name = "tb_customer")
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id", nullable = false)
	private UUID id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "identification", nullable = false)
	private String identification;
	
}
