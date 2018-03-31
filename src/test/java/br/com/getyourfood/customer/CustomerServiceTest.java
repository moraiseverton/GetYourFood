package br.com.getyourfood.customer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.getyourfood.GetYourFoodApplication;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = GetYourFoodApplication.class)
public class CustomerServiceTest {

    @MockBean
    private CustomerService service;

    @Test
    public void testValidCredentials() {
        Customer customer = new Customer();
        customer.setId(123l);
        customer.setName("Phoebe Buffay");
        customer.setEmail("phoebe@friends.com");
        customer.setPassword("central-perk94");

        given(service.getCustomerByEmail("phoebe@friends.com")).willReturn(customer);
        given(service.getCustomerByEmail("   PHOEBE@friends.com   ")).willReturn(customer);
        given(service.isValidCustomer(Mockito.anyString(), Mockito.anyString())).willCallRealMethod();

        assertTrue(service.isValidCustomer("phoebe@friends.com", "central-perk94"));
        assertTrue(service.isValidCustomer("   PHOEBE@friends.com   ", "central-perk94"));
    }

    @Test
    public void testInvalidCredentials() {
        given(service.getCustomerByEmail(Mockito.anyString())).willReturn(null);
        given(service.getCustomerByEmail(Mockito.anyString())).willCallRealMethod();

        assertFalse(service.isValidCustomer("", "central-perk94"));
        assertFalse(service.isValidCustomer("phoebe@friends.com", ""));
        assertFalse(service.isValidCustomer("phoebe@friends.com.br", "central-perk94"));
        assertFalse(service.isValidCustomer("phoebe@friends.com", "CENTRAL-perk94"));
        assertFalse(service.isValidCustomer("phoebe@friends.com", "     central-perk94    "));
    }
}
