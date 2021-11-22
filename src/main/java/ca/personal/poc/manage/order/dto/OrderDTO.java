/**
 * 
 */
package ca.personal.poc.manage.order.dto;

import java.time.ZonedDateTime;
import java.util.List;

import ca.personal.poc.manage.customer.dto.CustomerDTO;

import lombok.Data;

/**
 * @author Orlei Bicheski
 *
 */
@Data
public class OrderDTO {

	private ZonedDateTime date;
	
	private CustomerDTO customer;
	
	private List<ItemDTO> items;

}
