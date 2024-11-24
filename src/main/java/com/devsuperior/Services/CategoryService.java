package com.devsuperior.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Entitie.Category;
import com.devsuperior.Repositories.CategoryRepository;
import com.devsuperior.dto.CategoryDTO;


@Service
public class CategoryService{
	
	@Autowired
	private CategoryRepository repository;
	
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> result = repository.findAll();
		return result.stream().map(x-> new CategoryDTO(x)).toList();
	}
	
	
}