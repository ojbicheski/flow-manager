/**
 * 
 */
package ca.personal.poc.manage.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.personal.poc.manage.product.model.Product;

/** 
 * Spring Data repository for the Product(tb_product) entity. 
 * 
 * @author Orlei Bicheski
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
