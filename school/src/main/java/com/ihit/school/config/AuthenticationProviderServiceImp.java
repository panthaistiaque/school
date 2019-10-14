package com.ihit.school.config;

import com.ihit.school.model.Role;
import com.ihit.school.model.Users;
import com.ihit.school.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 23/Sep/2019.
 */
@Slf4j
@Component
public class AuthenticationProviderServiceImp implements AuthenticationProvider {
    @Autowired
    UsersService usersService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> grantList = new ArrayList<>();
        String name = authentication.getName();
        String password = ((String) authentication.getCredentials()).trim();

        //null check from user provided data
        if (name.equals("") || password.equals("")) {
            log.warn("Plz proved appropriate data");
            throw new BadCredentialsException("Plz proved appropriate data");
        }

        //check user credential from Database
        try {
            Users users = usersService.findByEmail(name);
            if (passwordEncoder.matches(password, users.getPassword())) {
                for (Role role : users.getRoleList()) {//multiple role assign for a single user
                    GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                    grantList.add(authority);
                }
                log.info("Successful Login by User : '" + name + "' & Role : " + grantList);
                return new UsernamePasswordAuthenticationToken(users, password, grantList);
            } else {
                log.warn("user proved wrong password ! for username : " + name + " & password : " + password);
                throw new BadCredentialsException("invalid username or password!");
            }
        } catch (NullPointerException e) {//when user not found in DB
            log.warn("invalid username or password! for username : " + name + " & password : " + password);
            throw new BadCredentialsException("invalid username or password!");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadCredentialsException("invalid username or password!");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
