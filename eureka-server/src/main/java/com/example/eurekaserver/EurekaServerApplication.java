package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

/*
    private static final String[] PUBLIC_ENDPOINTS = {
            "/",
            "/lastn",
            "/eureka/css/**",
            "/eureka/js/**",
            "/*.ico",
            "/eureka/fonts/**",
            "/eureka/images/**"
    };
*/

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @EnableWebSecurity
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().ignoringAntMatchers("/eureka/**");

/*
            http.authorizeRequests()
                    .antMatchers(PUBLIC_ENDPOINTS)
                    .permitAll();
*/

            super.configure(http);
        }
    }

}
