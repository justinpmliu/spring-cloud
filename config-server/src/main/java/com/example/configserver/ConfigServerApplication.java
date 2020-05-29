package com.example.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    @Value("${management.endpoints.web.exposure.include}#{T(java.util.Collections).emptyList()}")
    private List<String> managementEndpointsInclude;


    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @EnableWebSecurity
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().ignoringAntMatchers("/eureka/**");

/*
            String[] permittedEndpoints = Stream.of(
                    managementEndpointsInclude.stream().map(e -> "/actuator/" + e))
                    .flatMap(Function.identity())
                    .toArray(String[]::new);

            http.authorizeRequests()
                    .antMatchers(permittedEndpoints)
                    .permitAll();
*/

            super.configure(http);
        }
    }

}
