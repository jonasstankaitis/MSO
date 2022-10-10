package com.excelservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.excelservice.controler","com.excelservice.repository","com.excelservice.service"})
public class MsoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsoWsApplication.class, args);
	}

}
