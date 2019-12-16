package com.istiaque.EVM.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
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
    private final String[] UNAUTHENTICATED_URL = {"/login", "/singup", "/setpassword", "/v1/**", "/savePassword"};
    private final String[] ADMIN_URL = {"/syslog/**", "/allNewUserRequest", "/getOneNewRequestUser/**", "/getBrowserForUser/**", "/userRequestAccept","/newelection"};
    private final String[] USER_URL = {"/","/profile","/voterrequest","/notifi","/all_notification"};
    private final String[] COMMON_URL = {"/notifi","/all_notification"};

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("pantha@gmail.com").password(passwordEncoder().encode("1234")).roles("USER")
//                .and().withUser("admin@gmail.com").password(passwordEncoder().encode("admin")).roles("ADMIN");
        auth.authenticationProvider(authenticationProvider);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                antMatchers(USER_URL).hasAnyAuthority("USER").
                antMatchers(ADMIN_URL).hasAuthority("ADMIN").
                antMatchers(UNAUTHENTICATED_URL).permitAll().
                anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/login").usernameParameter("username").passwordParameter("password").
                and().
                exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/bower_components/**", "/dist/**", "/plugins/**");
    }
}
