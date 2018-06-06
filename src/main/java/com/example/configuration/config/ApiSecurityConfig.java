package com.example.configuration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter{


        @Autowired
        AuthenticationProvider authenticationProvider;


        @Override
        protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().
                antMatchers("/proper").permitAll().
                antMatchers("/test").hasAuthority("USER").
                antMatchers("/adminview").hasAuthority("ADMIN").
                anyRequest().authenticated().
                and().formLogin();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.authenticationProvider(authenticationProvider);

        }





}
