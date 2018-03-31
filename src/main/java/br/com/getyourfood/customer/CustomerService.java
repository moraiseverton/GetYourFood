package br.com.getyourfood.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer getCustomerByEmail(String email) {
        return repository.findByEmail(email.toLowerCase().trim());
    }

    public Customer createNewCustomer(Customer customer) {
        return repository.save(customer);
    }

    public boolean isValidCustomer(String email, String password) {
        Customer customer = getCustomerByEmail(email);
        if (customer != null) {
            return isValidPassword(customer.getPassword(), password);
        }
        return false;
    }

    public Customer getAuthenticatedCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getCustomerByEmail(auth.getName());
    }

    private boolean isValidPassword(String truePassword, String password) {
        return truePassword.equals(password);
    }
}
