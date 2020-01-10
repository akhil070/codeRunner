package com.cvr.it.coderunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class CoderunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderunnerApplication.class, args);
	}

}
