package com.devsuperior.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.Services.UserService;
import com.devsuperior.dto.UserDTO;


@RestController
@RequestMapping("/users")
public class UserController {

	//private ProductRepository repository;
	@Autowired
	private UserService service;
	
    
    // Para adaptar um parâmetro na rota, utilizamos o @GetMapping conforme abaixo:
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
		
		UserDTO dto = service.getMe();
		return ResponseEntity.ok(dto);
        
    }

	
}
