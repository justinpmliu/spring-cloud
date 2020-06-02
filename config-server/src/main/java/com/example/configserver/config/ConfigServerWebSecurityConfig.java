package com.example.configserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@EnableWebSecurity
public class ConfigServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${management.endpoints.web.exposure.include}#{T(java.util.Collections).emptyList()}")
    private List<String> managementEndpointsInclude;


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
