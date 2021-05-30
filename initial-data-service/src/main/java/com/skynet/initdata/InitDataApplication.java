package com.skynet.initdata;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
@ComponentScan(basePackages = {"com.skynet.initdata"})
@EntityScan({"com.skynet.commons", "com.skynet.initdata"})
@EnableJpaRepositories("com.skynet")
public class InitDataApplication {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(InitDataApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        final ConfigurableApplicationContext run = springApplication.run(args);
        //Todo: Once all the excution gets completed service will get stopped
        //run.close();
    }

}