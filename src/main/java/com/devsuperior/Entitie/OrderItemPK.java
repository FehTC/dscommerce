package com.devsuperior.Entitie;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderItemPK implements Serializable{


	private static final long serialVersionUID = 1L;

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