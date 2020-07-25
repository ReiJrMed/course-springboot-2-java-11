package com.empresa.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.course.entities.Category;
import com.empresa.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;//só é injetado com @Autowired se a classe CategoryService for registrada como componente @Component
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> categories = service.findAll();
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping("/{id}") //indica que vai usar como parâmetro o Id do banco de dados
	public ResponseEntity<Category> findById(@PathVariable Long id){
		//@PathVariable indica o parâmetro que será passado, deve vir antes da declaração do parâmetro
		Category user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
}
