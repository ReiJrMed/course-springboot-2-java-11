package com.empresa.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.course.entities.Order;
import com.empresa.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;//só é injetado com @Autowired se a classe OrderService for registrada como componente @Component
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> orders = service.findAll();
		return ResponseEntity.ok().body(orders);
	}
	
	@GetMapping("/{id}") //indica que vai usar como parâmetro o Id do banco de dados
	public ResponseEntity<Order> findById(@PathVariable Long id){
		//@PathVariable indica o parâmetro que será passado, deve vir antes da declaração do parâmetro
		Order order = service.findById(id);
		return ResponseEntity.ok().body(order);
	}
	
}
