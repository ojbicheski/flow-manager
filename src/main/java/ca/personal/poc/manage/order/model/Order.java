/**
 * 
 */
package ca.personal.poc.manage.order.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import ca.personal.poc.manage.customer.model.Customer;
import ca.personal.poc.manage.order.exception.OrderException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Orlei Bicheski
 *
 */
@Entity
@Table(schema = "sales", name = "tb_order")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", nullable = false)
	private UUID id;

	@Column(name = "date_ts", nullable = false)
	private ZonedDateTime date;

	@Column(name = "total", nullable = false)
	private BigDecimal total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Set<Item> items = new HashSet<>();
	
	@PrePersist
	public void prePersist() {
		this.date= ZonedDateTime.now(); 
	}
	
	public void addItem(Item item) {
		if (Objects.isNull(item)) {
			throw new OrderException("Invalid Item to be added into Order.");
		}
		if (Objects.isNull(total)) {
			total = BigDecimal.ZERO;
		}
		
		items.add(item);
		item.setOrder(this);
		total = total.add(item.getTotal());
	}

}
