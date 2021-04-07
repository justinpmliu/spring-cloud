package com.example.roomservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class RoomServicesApplication {

    @Value("${spring.lifecycle.timeout-per-shutdown-phase:null}")
    private String shutdownTimeout;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Room").select()
                .apis(RequestHandlerSelectors.basePackage("com.example.room"))
                .paths(any()).build().apiInfo(
                    new ApiInfo("Room Services", "A set of services to provide data access to rooms", "1.0.0", null, 
                    new Contact("Frank Moley", null, null), null, null, Collections.emptyList()));
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(4);
        if (!"null".equals(shutdownTimeout)) {
            taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
            taskExecutor.setAwaitTerminationSeconds(Integer.parseInt(shutdownTimeout.substring(0, shutdownTimeout.length() - 1)));
        }
        return taskExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(RoomServicesApplication.class, args);
    }

}
