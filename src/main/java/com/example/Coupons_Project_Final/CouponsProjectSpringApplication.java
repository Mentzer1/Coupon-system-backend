package com.example.Coupons_Project_Final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CouponsProjectSpringApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext ctx = SpringApplication.run(CouponsProjectSpringApplication.class, args);
		System.out.println("Server is up and running");
//		Test test = ctx.getBean(Test.class);
//
//
//
//		test.testAll();

	}

//	@Bean
//	public List<String> activeTokens(){
//		return new ArrayList<>();
//	}

}
