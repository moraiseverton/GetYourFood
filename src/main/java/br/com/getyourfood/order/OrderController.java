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

import br.com.getyourfood.product.Product;
import br.com.getyourfood.product.ProductRepository;
import br.com.getyourfood.store.Store;
import br.com.getyourfood.store.StoreRepository;

@RestController
@RequestMapping(value = "/Order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private OrderRepository orderRepository;
    private StoreRepository storeRepository;
    private ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, StoreRepository storeRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    @Transactional
    public Order createNewOrder(@Valid @RequestBody Order order) {
        refreshOrder(order);
        return orderRepository.save(order);
    }

    private void refreshOrder(Order order) {
        // TODO change to a better solution using java8, or JodaTime
        order.setLastUpdate(new Date());

        Store selectedStore = storeRepository.findById(order.getStoreId()).get();
        order.setStore(selectedStore);

        refreshProducts(order);
    }

    private void refreshProducts(Order order) {
        order.getOrderItems().forEach(orderItem -> {
            Product selectedProduct = productRepository.findById(orderItem.getProductId()).get();
            orderItem.setProduct(selectedProduct);
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setTotal(orderItem.getPrice() * orderItem.getQuantity());
        });

        double total = order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getTotal()).sum();
        order.setTotal(total);
    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable("orderId") Long orderId) {
        return orderRepository.findById(orderId).get();
    }
}
