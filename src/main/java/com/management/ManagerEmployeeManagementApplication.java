package com.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Dhiraj R Patil
 *
 */

@SpringBootApplication
@PropertySource(value = { "file:${location}/management_${env}.properties" })
public class ManagerEmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerEmployeeManagementApplication.class, args);
	}

}
