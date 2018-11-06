package com.example.wustNeo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class WustNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(WustNeo4jApplication.class, args);
	}


//	@Bean
//	String demo(PersonRepository personRepository){
//		personRepository.deleteAll();
//
//		PersonId greg=new PersonId("Greg");
//		greg.setAge("18");
//		PersonId roy=new PersonId("Roy");
//		roy.setAge("19");
//		PersonId craig=new PersonId("Craig");
//		craig.setAge("20");
//		personRepository.save(greg);
//		personRepository.save(roy);
//		personRepository.save(craig);
//
//
//		greg = personRepository.findByName(greg.getName());
//		greg.worksWith(roy);
//		greg.worksWith(craig);
//		personRepository.save(greg);
//
//		roy = personRepository.findByName(roy.getName());
//		roy.worksWith(craig);
//		// We already know that roy works with greg
//		personRepository.save(roy);
//
//		return "";
//
//	}


}
