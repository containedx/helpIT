package com.helpit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private DataSource dataSource;

    @Autowired
    public SecurityConfig (BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/panel")
                .hasAnyRole("FOUNDATION", "VOLUNTEER", "ADMIN")
                .antMatchers("/", "/**")
                .access("permitAll")
                .antMatchers("events/add").hasRole("FOUNDATION")
                .antMatchers("events/sign/*").hasRole("VOLUNTEER")

                .and().formLogin().loginPage("/login").failureUrl("/login").permitAll()

                .and().logout().logoutSuccessUrl("/")

                .and().csrf().ignoringAntMatchers("/h2-console/**")

                .and().headers().frameOptions().sameOrigin();
    }






}
