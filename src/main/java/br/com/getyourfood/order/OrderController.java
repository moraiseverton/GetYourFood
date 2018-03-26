package br.com.getyourfood.order;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Order createNewOrder(@Valid @RequestBody Order order) {
        return service.createNewOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable("orderId") Long orderId) {
        return service.findById(orderId);
    }

    @GetMapping("/customer")
    public List<Order> listOrdersFromAuthCustomer() {
        return service.listOrdersFromAuthCustomer();
    }
}
