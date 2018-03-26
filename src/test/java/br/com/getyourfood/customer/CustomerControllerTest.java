package br.com.getyourfood.customer;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

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
        customer.setCreation(LocalDateTime.now());

        StringBuffer jsonContent = new StringBuffer();
        jsonContent.append("{")
                   .append("      \"email\":    \"" + customer.getEmail())
                   .append("\"  , \"name\":     \"" + customer.getName())
                   .append("\"  , \"address\":  \"" + customer.getAddress())
                   .append("\"  , \"creation\": \"" + customer.getCreation().toString())
                   .append("\"  , \"password\": \"" + customer.getPassword())
                   .append("\" }");

        given(controller.createNewCustomer(customer)).willReturn(" { \"message\": 'Logged as " + customer.getEmail() + ".' } ");

        mockMvc.perform(post("/Customer").contentType(APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonContent.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void authInvalidCustomer() throws Exception {
        String email = "phoebe@buffay.com";
        String password = "wrong password";

        StringBuffer jsonContent = new StringBuffer();
        jsonContent.append("{")
                   .append("      \"email\":    \"" + email)
                   .append("\"  , \"password\": \"" + password)
                   .append("\" }");

        // TODO: find out what is going on
        when(controller.auth(email, password)).thenReturn("{ \"error\": 'User and password not found.' }");

        mockMvc.perform(post("/Customer/auth").param("email", email).param("password", password)
                .contentType(APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonContent.toString()))
                .andExpect(status().isBadRequest());
    }
}
