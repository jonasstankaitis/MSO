package com.excelservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.excelservice.controler","com.excelservice.repository","com.excelservice.service","com.excelservice.config"})
public class MsoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsoWsApplication.class, args);
	}

}
