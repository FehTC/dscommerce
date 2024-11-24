package com.devsuperior.Controllers;



import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.Services.OrderService;
import com.devsuperior.dto.OrderDTO;


@RestController
@RequestMapping("/orders")
public class OrderController {

	//private ProductRepository repository;
	@Autowired
	private OrderService service;
	
    
    // Para adaptar um parâmetro na rota, utilizamos o @GetMapping conforme abaixo:
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById( @PathVariable  Long id) { // @PathVariable = Configurando o parâmetro de rota para casar com o ID
														// do parâmetro do método
		OrderDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
        
    }
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	@PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) { 
														
   		dto = service.insert(dto);
   		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
