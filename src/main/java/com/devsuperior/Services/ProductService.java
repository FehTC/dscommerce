package com.devsuperior.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Entitie.Product;
import com.devsuperior.Repositories.ProductRepository;
import com.devsuperior.dto.ProductDTO;


@Service
public class ProductService{
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
	
}