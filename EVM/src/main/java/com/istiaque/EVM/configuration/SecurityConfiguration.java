package com.istiaque.EVM.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final String[] UNAUTHENTICATED_URL = {"/login", "/singup", "/h2-console/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("pantha@gmail.com").password(passwordEncoder().encode("1234")).roles("USER")
                .and().withUser("admin@gmail.com").password(passwordEncoder().encode("admin")).roles("ADMIN");
//        auth.authenticationProvider(authenticationProvider);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().antMatchers(UNAUTHENTICATED_URL).permitAll().
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/bower_components/**", "/dist/**", "/plugins/**");
    }
}
