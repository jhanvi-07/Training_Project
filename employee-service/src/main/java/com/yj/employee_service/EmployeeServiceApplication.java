package com.yj.employee_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info=@Info(
				title="Employee Microservice",
				description="Employee Microservice for UKG Training",
				summary="Employee CRUD Operations",
				contact=@Contact(
						name="Jhanvi",
						email="jhanvi@gmail.com",
						url="example.www.com"
				)
		)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef ="auditAwareImpl")
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
