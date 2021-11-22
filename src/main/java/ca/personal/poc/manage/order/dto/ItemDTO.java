/**
 * 
 */
package ca.personal.poc.manage.order.dto;

import java.util.UUID;

import lombok.Data;

/**
 * @author Orlei Bicheski
 *
 */
@Data
public class ItemDTO {

	private UUID productID;
	
	private Integer quantity;
	
}
