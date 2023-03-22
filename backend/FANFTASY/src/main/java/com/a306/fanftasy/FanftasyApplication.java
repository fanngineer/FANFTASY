package com.a306.fanftasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude = DataSourceAutoConfiguration.class)
public class FanftasyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanftasyApplication.class, args);
	}

}
