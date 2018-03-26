package br.com.getyourfood.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    //@Autowired
    //private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public Customer getCustomerByEmail(String email) {
        return repository.findByEmail(email.toLowerCase());
    }

    public Customer createNewCustomer(Customer customer) {
        Customer newCustomer = repository.save(customer);

        createNewUser(newCustomer.getEmail(), newCustomer.getPassword());
        authUser(newCustomer.getEmail(), newCustomer.getPassword());

        return newCustomer;
    }

    public boolean isValidCustomer(String email, String password) {
        Customer customer = repository.findByEmail(email.toLowerCase());
        if (customer != null) {
            return authUser(email, password);
        }
        return false;
    }

    public Customer getAuthenticatedCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getCustomerByEmail(auth.getName());
    }

    private void createNewUser(String user, String password) {
        //inMemoryUserDetailsManager.createUser(new User(user, password, new ArrayList<GrantedAuthority>()));
        authUser(user, password);
    }

    private boolean authUser(String user, String password) {
//        if (user != null && password.equals(password)) {
//            UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(user);
//            return userDetails != null;
//        }
//        return false;
        return (user != null && password.equals(password));
    }
}
