package com.empresa.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.empresa.course.entities.Category;
import com.empresa.course.repositories.CategoryRepository;

//@Component //permite essa classe ser instanciada automaticamente com @Autowired se ela for um componente
@Service //para ser mais específico indicando que é um serviço e ela ser instanciada automaticamente com @Autowired, prioriza-se ser específico
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id){
		Optional<Category> optionalCategory = categoryRepository.findById(id); //esse método retorna um valor tipo optional
		return optionalCategory.get(); //.get() resgata o objeto instanciado no optional que é o Category
	}

}
