package com.helpit.security;

import com.helpit.services.UserService;
import com.helpit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService registerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = registerService.findUser(name);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }
        if (!bCryptPasswordEncoder.matches(password,user.getPassword()) ) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().getRole();
            }
        };
        authorities.add(grantedAuthority);

        return new UsernamePasswordAuthenticationToken(user.getEmail(), password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}

