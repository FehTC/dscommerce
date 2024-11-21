package com.devsuperior.Controllers;



import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<ProductDTO> findById( @PathVariable  Long id) { // @PathVariable = Configurando o parâmetro de rota para casar com o ID
														// do parâmetro do método
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
        
    }

   	@GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam(name="name",defaultValue="") String name,Pageable pageable) { 
		Page<ProductDTO> dto = service.findAll(name,pageable);									
   		return ResponseEntity.ok(dto);
        
    }
   	
  
	/*
 	@GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) { 
		Page<ProductDTO> dto = service.findAll(pageable);									
   		return ResponseEntity.ok(dto);
 	} 
	*/
   	
   	@PreAuthorize("hasRole('ROLE_ADMIN')")
 	@PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) { 
														
   		dto = service.insert(dto);
   		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
   	
   	@PreAuthorize("hasRole('ROLE_ADMIN')")
  	@PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,@Valid @RequestBody ProductDTO dto) { 									
		dto = service.update(id,dto);
		return ResponseEntity.ok(dto);
        
    }
  	
   	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { 									
		service.delete(id);
		return ResponseEntity.noContent().build();
        
    }
	
}
