package com.empresa.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.empresa.course.entities.User;
import com.empresa.course.repositories.UserRepository;

@Configuration //indica que é uma classe de configuração
@Profile("test")//especifica o perfil (definido em application.properties e application-test.properties) que será configurado 
public class TestConfig implements CommandLineRunner{
	//classe que vai povoar o banco de dados h2(banco específico pata testes) teste
	//A implementação de CommandLineRunner garante que isso vai executar no início da execução desta aplicação
	
	@Autowired //garante o acoplamento fraco do UserRepository, com isso o SpringBoot o instancia automaticamente
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		//método do contrato da interface CommandLineRunner para executar no início da execução desta aplicação
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		//método para salvar no banco de dados um conjunto de objetos, no caso, foi instanciada uma lista com o método Arrays.asList
	}
	
	
}
