package com.devsuperior.Controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.Services.CategoryService;
import com.devsuperior.dto.CategoryDTO;


@RestController
@RequestMapping("/categories")
public class CategoryController {

	//private ProductRepository repository;
	@Autowired
	private CategoryService service;
	
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() { 
    	List<CategoryDTO> list = service.findAll();
   		return ResponseEntity.ok(list);
        
    }
   	
}
