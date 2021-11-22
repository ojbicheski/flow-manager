package ca.personal.poc.manage.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ca.personal.poc.manage.product.exception.ProductException;
import ca.personal.poc.manage.product.model.Product;
import ca.personal.poc.manage.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Service
@Slf4j
public class ProductService {

	private final ProductRepository repository;

	/**
	 * @param repository
	 */
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public List<Product> findAllById(List<UUID> ids) {
		List<Product> products = repository.findAllById(ids);
		
		if (products.size() != ids.size()) {
			log.error("Product list size returned is different than ID list size");
			throw new ProductException("Product list size returned is different than ID list size");
		}
		
		return products;
	}
}
