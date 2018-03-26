package br.com.getyourfood.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.getyourfood.customer.CustomerService;

@Component
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private CustomerService service;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        if (service.isValidCustomer(username, password)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(token);

            return token;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}