package com.empresa.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.empresa.course.entities.Product;
import com.empresa.course.repositories.ProductRepository;

//@Component //permite essa classe ser instanciada automaticamente com @Autowired se ela for um componente
@Service //para ser mais específico indicando que é um serviço e ela ser instanciada automaticamente com @Autowired, prioriza-se ser específico
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id){
		Optional<Product> optionalProduct = productRepository.findById(id); //esse método retorna um valor tipo optional
		return optionalProduct.get(); //.get() resgata o objeto instanciado no optional que é o Product
	}

}
