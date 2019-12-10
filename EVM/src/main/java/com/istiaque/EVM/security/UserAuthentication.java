package com.istiaque.EVM.security;

import com.istiaque.EVM.model.Menu;
import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.RoleModel;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.service.ProfileService;
import com.istiaque.EVM.service.UserService;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 11/11/2019.
 */
@Slf4j
@Component
public class UserAuthentication implements AuthenticationProvider {
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    HttpSession session;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantList = new ArrayList<>();
        User user;
        try {

            user = userService.getUserByUserName(userName);
            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    List list = new ArrayList();
                    for (RoleModel role : user.getRoleList()) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                        grantList.add(authority);
                        for (Menu m : role.getMenuList()) {
                            list.add(m);
                        }
                    }
                    Profile profile = profileService.findByUser(user);
                    Collections.sort(list);
                    session.setAttribute("menu", list);
                    session.setAttribute("profile", profile==null?new Profile():profile);
                    return new UsernamePasswordAuthenticationToken(user, password, grantList);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        throw new BadCredentialsException("invalid username or password!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
