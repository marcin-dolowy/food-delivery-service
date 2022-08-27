package com.epam.training.fooddelivery.security;

import com.epam.training.fooddelivery.domain.Customer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeaderUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public HeaderUsernamePasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher);
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String email = request.getHeader("email");
        String password = request.getHeader("password");
        if(email == null || password == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        if(authResult.isAuthenticated()){
            MyUserPrincipal principal = (MyUserPrincipal) authResult.getPrincipal();
            Customer customer = principal.getCustomer();
            request.getSession().setAttribute("id",customer.getId());
            SecurityContextHolder.getContext().setAuthentication(authResult);
            chain.doFilter(request,response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
