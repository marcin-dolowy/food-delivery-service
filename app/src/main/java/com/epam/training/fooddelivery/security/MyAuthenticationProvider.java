package com.epam.training.fooddelivery.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final MyUserDetailsService detailsService;

    public MyAuthenticationProvider(MyUserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = detailsService.loadUserByUsername(email);
        if (userDetails != null){
            if (userDetails.getPassword().equals(password)){
                return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            }
        }
        authentication.setAuthenticated(false);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
