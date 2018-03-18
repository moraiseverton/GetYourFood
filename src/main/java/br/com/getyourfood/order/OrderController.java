package br.com.getyourfood.order;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    @Transactional
    public Order createNewOrder(@Valid @RequestBody Order order) {
        if (order.getId() > 0) {
            // TODO change to a better solution using java8
            order.setLastUpdate(new Date());
        }
        return orderRepository.save(order);
    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable("orderId") Long orderId) {
        return orderRepository.findById(orderId).get();
    }
}
