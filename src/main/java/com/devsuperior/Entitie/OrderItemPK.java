package com.devsuperior.Entitie;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK {

	@ManyToOne
	@JoinColumn(name = "order_id") // Chave estrangeira
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id") // Chave estrangeira
	private Product product;

	public OrderItemPK() {

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}