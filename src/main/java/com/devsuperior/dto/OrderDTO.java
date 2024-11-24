package com.devsuperior.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.Entitie.Order;
import com.devsuperior.Entitie.OrderItem;
import com.devsuperior.Entitie.OrderStatus;

public class OrderDTO {

	private Long id;
	private Instant moment;
	private OrderStatus status;
	
	private ClientDTO client;
	private PaymentDTO payment;
	
	private List<OrderItemDTO> items = new ArrayList<>();

	public OrderDTO(Order entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.status = entity.getStatus();
		this.client = new ClientDTO(entity.getClient());
		this.payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		for (OrderItem item: entity.getItems()) {
			items.add(new OrderItemDTO(item));
		}
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItemDTO item: items) {
			sum = sum + item.getSubtotal();
		}
		return sum;
		
	}
	

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}
		
	
}
