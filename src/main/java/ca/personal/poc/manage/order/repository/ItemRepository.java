/**
 * 
 */
package ca.personal.poc.manage.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.personal.poc.manage.order.model.Item;

/** 
 * Spring Data repository for the Item(tb_item) entity. 
 * 
 * @author Orlei Bicheski
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

}
