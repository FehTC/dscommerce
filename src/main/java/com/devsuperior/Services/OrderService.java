package com.devsuperior.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Entitie.Order;
import com.devsuperior.Repositories.OrderRepository;
import com.devsuperior.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.dto.OrderDTO;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
			Order order = repository.findById(id).orElseThrow(()->new ResourceNotFoundException ("Recurso não encontrado"));
			return new OrderDTO(order);
	}
	
	
}
