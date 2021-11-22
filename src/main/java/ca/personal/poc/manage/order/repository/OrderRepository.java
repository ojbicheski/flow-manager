/**
 * 
 */
package ca.personal.poc.manage.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.personal.poc.manage.order.model.Order;

/** 
 * Spring Data repository for the Order(tb_order) entity. 
 * 
 * @author Orlei Bicheski
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

}
