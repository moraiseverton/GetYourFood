package br.com.getyourfood.customer;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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
        customer.setCreation(Instant.now());

        StringBuffer jsonContent = new StringBuffer();
        jsonContent.append("{")
                   .append("      \"email\":    \"" + customer.getEmail())
                   .append("\"  , \"name\":     \"" + customer.getName())
                   .append("\"  , \"address\":  \"" + customer.getAddress())
                   .append("\"  , \"creation\": \"" + customer.getCreation().toString())
                   .append("\"  , \"password\": \"" + customer.getPassword())
                   .append("\" }");

        given(controller.createNewCustomer(customer)).willReturn(customer);

        mockMvc.perform(post("/Customer").contentType(APPLICATION_JSON).content(jsonContent.toString()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
