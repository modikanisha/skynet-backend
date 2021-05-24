package com.skynet.employeemgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication()
//@ComponentScan(basePackages = {"com.skynet.employeemgmt","com.skynet.commons"})
////@EnableJpaRepositories("com.skynet.employeemgmt.repository")
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, DataSourceAutoConfiguration.class})

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.skynet"})
//@EntityScan({"com.hhstechgroup.ryl.commons.repository.models", "com.hhstechgroup.initdata"})
@EnableJpaRepositories({"com.skynet.employeemgmt.repository"})


public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}