package br.com.getyourfood.customer;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.getyourfood.base.BaseControllerTest;

public class CustomerControllerTest extends BaseControllerTest {

    @MockBean
    private CustomerController controller;

    @Test
    public void createNewCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(123l);
        customer.setName("Phoebe Buffay");
        customer.setEmail("phoebe@buffay.com");
        customer.setAddress("555 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        customer.setPassword("friends");

        given(controller.createNewCustomer(customer)).willReturn(customer);

        mockMvc.perform(post("/Customer")
                .contentType(APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isCreated());
    }

    @Test
    public void authInvalidCustomer() throws Exception {
        String email = "phoebe@buffay.com";
        String password = "wrong password";

        ResponseEntity<String> response = ResponseEntity.badRequest().body("User and password not found.");

        given(controller.auth(email, password)).willReturn(response);

        mockMvc.perform(post("/Customer/auth").param("email", email).param("password", password)
                .contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User and password not found."));
    }

    @Test
    public void authValidCustomer() throws Exception {
        String email = "phoebe@buffay.com";
        String password = "friends";

        ResponseEntity<String> response = ResponseEntity.ok("Logged as " + email + ".");

        given(controller.auth(email, password)).willReturn(response);

        mockMvc.perform(post("/Customer/auth").param("email", email).param("password", password)
                .contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Logged as phoebe@buffay.com."));
    }
}
