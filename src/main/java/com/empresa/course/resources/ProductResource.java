package com.empresa.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.course.entities.Product;
import com.empresa.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;//só é injetado com @Autowired se a classe ProductService for registrada como componente @Component
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> categories = service.findAll();
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping("/{id}") //indica que vai usar como parâmetro o Id do banco de dados
	public ResponseEntity<Product> findById(@PathVariable Long id){
		//@PathVariable indica o parâmetro que será passado, deve vir antes da declaração do parâmetro
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
}
