package com.empresa.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.course.entities.Product;

//não é necessário colocar o @Repository, pois por herdar JpaRepository, automaticamente é um componente do SpringBoot
public interface ProductRepository extends JpaRepository<Product, Long>{
	//JpaRepository<entidade, tipo do Id>
	//Não precisa criar implementação para esta interface, pois o SpringDataJPA já tem uma implementação padrão
	//(dese jeito tá pronto a nossa interface)
}
