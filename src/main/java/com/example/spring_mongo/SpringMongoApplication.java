package com.example.spring_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

//@SpringBootApplication()
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class , MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SpringMongoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMongoApplication.class, args);
	}

}
