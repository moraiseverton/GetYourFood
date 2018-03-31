package br.com.getyourfood.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.getyourfood.auth.AuthProviderService;

@RestController
@RequestMapping(value = "/Customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private AuthProviderService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomer(@Valid @RequestBody Customer customer) {
        return service.createNewCustomer(customer);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestParam("email") String email, @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authService.authenticate(credentials);

        if (auth != null) {
            return ResponseEntity.ok("Logged as " + auth.getName() + ".");
        }

        return ResponseEntity.badRequest().body("User and password not found.");
    }
}
