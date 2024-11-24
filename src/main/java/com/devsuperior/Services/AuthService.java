package com.devsuperior.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.Entitie.User;
import com.devsuperior.Services.exceptions.ForbiddenException;

@Service
public class AuthService {

	@Autowired
	private UserService userService;
	
	public void validateSelfOrAdmin(Long userId) {
		
		// Se o usuário que estiver logado, não for admin ou dono do pedido. Será lançada a excessão 403 (Forbidden)
		User me = userService.authenticated(); // Com isso temos o usuário que está logado
		
		
		
		// se o usuário não for admin e o Id não for igual a um usuário recebido como parâmetro.
		if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId) ) { 
			throw new ForbiddenException("Acess denied");
		}
		
	}
	
}
