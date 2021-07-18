package com.pwc.complaintmanagement.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
//@EnableWebSecurity
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.GET, "/users");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                //.antMatchers("**/rest/*")
                .and()
                //.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
                .httpBasic();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication();
        auth.inMemoryAuthentication().withUser("user").password("123456").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        //User Role
        UserDetails theUser = User.withUsername("user")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("123456").roles("USER").build();

        //Manager Role
        UserDetails theManager = User.withUsername("john")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("654321").roles("ADMIN").build();


        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(theUser);
        userDetailsManager.createUser(theManager);

        return userDetailsManager;

    }
}
