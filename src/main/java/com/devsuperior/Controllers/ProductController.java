package com.devsuperior.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.Services.ProductService;
import com.devsuperior.dto.ProductDTO;


@RestController
@RequestMapping("/products")
public class ProductController {

	//private ProductRepository repository;
	@Autowired
	private ProductService service;
	
    
    // Para adaptar um parâmetro na rota, utilizamos o @GetMapping conforme abaixo:
	@GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) { // @PathVariable = Configurando o parâmetro de rota para casar com o ID
														// do parâmetro do método
		ProductDTO dto = service.findById(id);
		return dto;
        
    }
	
   	@GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) { 
														
   		return service.findAll(pageable);
        
    }
	
}
