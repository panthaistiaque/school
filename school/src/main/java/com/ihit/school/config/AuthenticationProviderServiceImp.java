package com.ihit.school.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 23/Sep/2019.
 */
@Component
public class AuthenticationProviderServiceImp implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> grantList = new ArrayList<>();
        String name = authentication.getName();
        String password = ((String) authentication.getCredentials()).trim();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
