package com.skynet.employeemgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication()
//@ComponentScan(basePackages = {"com.skynet.employeemgmt","com.skynet.commons"})
////@EnableJpaRepositories("com.skynet.employeemgmt.repository")
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, DataSourceAutoConfiguration.class})

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.skynet"})
@EntityScan({"com.skynet.commons"})
@EnableJpaRepositories({"com.skynet.employeemgmt.repository"})


public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}