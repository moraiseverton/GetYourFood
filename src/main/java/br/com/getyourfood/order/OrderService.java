package br.com.getyourfood.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.getyourfood.customer.Customer;
import br.com.getyourfood.customer.CustomerService;
import br.com.getyourfood.product.Product;
import br.com.getyourfood.product.ProductRepository;
import br.com.getyourfood.store.Store;
import br.com.getyourfood.store.StoreRepository;

@Service
public class OrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createNewOrder(Order order) {
        refreshOrder(order);
        return orderRepository.save(order);
    }

    private void refreshOrder(Order order) {
        order.setLastUpdate(LocalDateTime.now());

        loadStore(order);
        loadCustomer(order);
        loadProducts(order);
    }

    private void loadCustomer(Order order) {
        Customer customer = customerService.getAuthenticatedCustomer();
        order.setCustomer(customer);
    }

    private void loadStore(Order order) {
        Store selectedStore = storeRepository.findById(order.getStoreId()).get();
        order.setStore(selectedStore);
    }

    private void loadProducts(Order order) {
        order.getOrderItems().forEach(orderItem -> {
            Product selectedProduct = productRepository.findById(orderItem.getProductId()).get();

            orderItem.setTheOrder(order);
            orderItem.setProduct(selectedProduct);
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setTotal(orderItem.getPrice() * orderItem.getQuantity());
        });

        double total = order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getTotal()).sum();
        order.setTotal(total);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    public List<Order> listOrdersFromAuthCustomer() {
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();
        return orderRepository.findAllByCustomerId(authenticatedCustomer.getId());
    }
}
