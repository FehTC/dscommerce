package com.devsuperior.Services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Entitie.Category;
import com.devsuperior.Entitie.Product;
import com.devsuperior.Repositories.ProductRepository;
import com.devsuperior.Services.exceptions.DataBaseException;
import com.devsuperior.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.dto.CategoryDTO;
import com.devsuperior.dto.ProductDTO;
import com.devsuperior.dto.ProductMinDTO;


@Service
public class ProductService{
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
			Product product = repository.findById(id).orElseThrow(()->new ResourceNotFoundException ("Recurso não encontrado"));
			return new ProductDTO(product);
	}
	
	@Transactional(readOnly = true)
	public Page<ProductMinDTO> findAll(String name,Pageable pageable) {
		Page<Product> result = repository.searchByName(name, pageable);
		return result.map(x-> new ProductMinDTO(x));
	}
	
	/*
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x-> new ProductDTO(x));
	}
		*/
	
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
		
		try {
			// Preparamos o objeto copiando os dados do dto
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
			// Salvamos no banco dados
			entity = repository.save(entity);
			// Retornamos o objeto salvo e atualizado
			return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Recurso não encontrado");
		}
	
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}
	
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		// TODO Auto-generated method stub
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
		entity.getCategories().clear(); // Limpa a lista que tinha e insere as nova categorias que enviamos abaixo
		
		for(CategoryDTO catDto: dto.getCategories()) {
			Category cat = new Category();
			cat.setId(catDto.getId());
			entity.getCategories().add(cat);
		}
		
	}
	
	
	
	
	
	
}