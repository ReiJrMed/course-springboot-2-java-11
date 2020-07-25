package com.empresa.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.empresa.course.entities.Category;
import com.empresa.course.entities.Order;
import com.empresa.course.entities.Product;
import com.empresa.course.entities.User;
import com.empresa.course.enums.OrderStatus;
import com.empresa.course.repositories.CategoryRepository;
import com.empresa.course.repositories.OrderRepository;
import com.empresa.course.repositories.ProductRepository;
import com.empresa.course.repositories.UserRepository;

@Configuration //indica que é uma classe de configuração
@Profile("test")//especifica o perfil (definido em application.properties e application-test.properties) que será configurado 
public class TestConfig implements CommandLineRunner{
	//classe que vai povoar o banco de dados h2(banco específico pata testes) teste
	//A implementação de CommandLineRunner garante que isso vai executar no início da execução desta aplicação
	
	@Autowired //garante o acoplamento fraco do UserRepository, com isso o SpringBoot o instancia automaticamente
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		//método do contrato da interface CommandLineRunner para executar no início da execução desta aplicação
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Instant padrão 8601, no caso a Instring com 'z' no final indica o instant no formato UTC (horário em GreenWich, não o local)
		//o banco de dados mostra um horário 3h atrasado, porque mostra o horário local da máquina que é do Brasil
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		//não muda, pois o banco de dados H2 é temporário
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		//método para salvar no banco de dados um conjunto de objetos, no caso, foi instanciada uma lista com o método Arrays.asList
	}	
}
