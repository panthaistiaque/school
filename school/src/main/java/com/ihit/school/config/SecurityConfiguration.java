package com.ihit.school.config;

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
 * Created by Mohammad Istiaque Hossain (DS00688) on 23/Sep/2019.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private String[] ADMIN_URL = {"/h2-console/**"};
    private String[] COMMON_URL = {"/home"};
    private String[] ACCESS_ALL_URL = {"/login","/newAccount","/newrequest"};
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http//cors().and().csrf().and()
                .authorizeRequests()
                .antMatchers(ADMIN_URL).hasAuthority("ADMIN")
                .antMatchers(COMMON_URL).hasAnyAuthority("USER","ADMIN","NOVICE")//hasAnyRole("USER","ADMIN","NOVICE")
                .antMatchers(ACCESS_ALL_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("logInId").passwordParameter("password")
                .defaultSuccessUrl("/home",true);
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/images/**", "/lib/**", "/stylesheets/**");
    }
}
