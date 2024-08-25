package com.devsuperior.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x-> new ProductDTO(x));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		// Preparamos o objeto copiando os dados do dto
		Product entity = new Product();
		copyDtoToEntity(dto,entity);		
		// Salvamos no banco dados
		entity = repository.save(entity);
		// Retornamos o objeto salvo e atualizado
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		
		// Preparamos o objeto copiando os dados do dto
		Product entity = repository.getReferenceById(id);
		copyDtoToEntity(dto,entity);
		// Salvamos no banco dados
		entity = repository.save(entity);
		// Retornamos o objeto salvo e atualizado
		return new ProductDTO(entity);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		// TODO Auto-generated method stub
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
	
	
	
}