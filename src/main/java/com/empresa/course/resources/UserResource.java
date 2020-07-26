package com.empresa.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.empresa.course.entities.User;
import com.empresa.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;//só é injetado com @Autowired se a classe UserService for registrada como componente @Component
	
	@GetMapping //quando se vai recuperar dados do banco de dados se usa a anotação com get
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
	
	//anotação "post" é usada para indicar que se vai alterar de alguma forma o banco de dados, o compilador já é avisado
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		//@RequestBody indica que o objeto vai chegar no modo Json e ser desserialiado no objeto solicitado
		user = service.insert(user);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();//método para obter a URI a ser usada no método created, que terá como endereço users/id(do objeto salvo)
		
		return ResponseEntity.created(uri).body(user);//método para retornar o código http 201 que indica a criação de um objeto
	}
	
	@DeleteMapping(value = "/{id}") //Anotação que indica deleção
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build(); //retorna o código 204, que indica deleção
	}
}
