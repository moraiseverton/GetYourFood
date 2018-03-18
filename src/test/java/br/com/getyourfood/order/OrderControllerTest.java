package br.com.getyourfood.order;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.com.getyourfood.base.BaseControllerTest;
import br.com.getyourfood.customer.Customer;

public class OrderControllerTest extends BaseControllerTest {

    @MockBean
    private OrderController controller;

    @Test
    public void createNewCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(123l);
        customer.setName("Phoebe Buffay");
        customer.setEmail("phoebe@buffay.com");
        customer.setAddress("555 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        customer.setPassword("friends");
        customer.setCreation(Instant.now());

        Order order = new Order();
        order.setCustomer(customer);

        StringBuffer jsonContent = new StringBuffer();
        jsonContent.append("{") 
                   .append("  \"date\": \"2018-03-18T21:45:29.711Z\",") 
                   .append("  \"customerId\": 123,") 
                   .append("  \"deliveryAddress\": \"432 Paulista Ave, Sao Paulo, Sao Paulo\",") 
                   .append("  \"contact\": \"+55 19 99104-0743\",") 
                   .append("  \"storeId\": 1,") 
                   .append("  \"orderItems\": [") 
                   .append("    {") 
                   .append("      \"productId\": 2,") 
                   .append("      \"product\": {") 
                   .append("        \"id\": 2,") 
                   .append("        \"storeId\": 44,") 
                   .append("        \"name\": \"brocolli pizza\",") 
                   .append("        \"description\": \"vegan brocolli pizza\",") 
                   .append("        \"price\": 25.22") 
                   .append("      },") 
                   .append("      \"price\": 25.22,") 
                   .append("      \"quantity\": 2,") 
                   .append("      \"total\": 50.44") 
                   .append("    }") 
                   .append("  ],") 
                   .append("  \"total\": 50.44,") 
                   .append("  \"status\": \"delivered\",") 
                   .append("  \"lastUpdate\": \"2018-03-18T22:45:29.711Z\"") 
                   .append("}");

        given(controller.createNewOrder(order)).willReturn(order);

        mockMvc.perform(post("/Customer").contentType(APPLICATION_JSON).content(jsonContent.toString()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
