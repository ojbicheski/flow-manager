/**
 * 
 */
package ca.personal.poc.manage.customer.dto;

import java.util.UUID;

import lombok.Data;

/**
 * @author Orlei Bicheski
 *
 */
@Data
public class CustomerDTO {

	private UUID id;
	
	private String firstName;

	private String lastName;
	
	private String idetification;
	
}
