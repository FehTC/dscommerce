package com.devsuperior.Services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Entitie.Order;
import com.devsuperior.Entitie.OrderItem;
import com.devsuperior.Entitie.OrderStatus;
import com.devsuperior.Entitie.Product;
import com.devsuperior.Entitie.User;
import com.devsuperior.Repositories.OrderItemRepository;
import com.devsuperior.Repositories.OrderRepository;
import com.devsuperior.Repositories.ProductRepository;
import com.devsuperior.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.dto.OrderDTO;
import com.devsuperior.dto.OrderItemDTO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		User user = userService.authenticated();
		order.setClient(user);

		for (OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}

}
