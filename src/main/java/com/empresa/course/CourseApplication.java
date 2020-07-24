package com.empresa.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}
   
	//a propriedade spring.jpa.open-in-view= true ou false indica se nas relações entre as entidades, a entidade que 
	// se relaciona com outra a instancia ou não pelo banco de dados, no caso da relação ManyToOne, assim
	//os valores da outra entidade pode ser instanciada ou não dependendo ser for true ou false
}
