package br.com.getyourfood.order;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.com.getyourfood.base.BaseControllerTest;
import br.com.getyourfood.customer.Customer;

public class OrderControllerTest extends BaseControllerTest {

    @MockBean
    private OrderController controller;

    @Test
    public void createNewOrder() throws Exception {
        Customer customer = new Customer();
        customer.setId(123l);
        customer.setName("Phoebe Buffay");
        customer.setEmail("phoebe@buffay.com");
        customer.setAddress("555 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        customer.setPassword("friends");
        customer.setCreation(LocalDateTime.now());

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(Collections.singletonList(new OrderItem()));

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

        mockMvc.perform(post("/Order").contentType(APPLICATION_JSON).content(jsonContent.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void findOrderByOrderId() throws Exception {
        Customer customer = new Customer();
        customer.setId(123l);
        customer.setName("Phoebe Buffay");
        customer.setEmail("phoebe@buffay.com");
        customer.setAddress("555 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        customer.setPassword("friends");
        customer.setCreation(LocalDateTime.now());

        Order order = new Order();
        order.setId(123l);
        order.setCustomer(customer);

        given(controller.findById(order.getId())).willReturn(order);

        mockMvc.perform(get("/Order/{orderId}", order.getId()).contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id", is(order.getId().intValue())));
    }
}
