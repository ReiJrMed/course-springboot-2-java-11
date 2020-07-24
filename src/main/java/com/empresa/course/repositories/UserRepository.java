package com.empresa.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//JpaRepository<entidade, tipo do Id>
	//Não precisa criar implementação para esta interface, pois o SpringDataJPA já tem uma implementação padrão
	//(dese jeito tá pronto a nossa interface)
}
