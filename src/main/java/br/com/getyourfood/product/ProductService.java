package br.com.getyourfood.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> listAllProducts() {
        return repository.findAll();
    }

    public List<Product> searchProductsByNameOrDescription(String searchText) {
        return repository.searchByName(searchText.toLowerCase());
    }

    public Product findProductById(Long productId) {
        return repository.findById(productId).get();
    }
}
