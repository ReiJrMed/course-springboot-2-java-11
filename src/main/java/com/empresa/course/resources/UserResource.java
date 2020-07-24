package com.empresa.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.course.entities.User;
import com.empresa.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;//só é injetado com @Autowired se a classe UserService for registrada como componente @Component
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}") //indica que vai usar como parâmetro o Id do banco de dados
	public ResponseEntity<User> findById(@PathVariable Long id){
		//@PathVariable indica o parâmetro que será passado, deve vir antes da declaração do parâmetro
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
}
