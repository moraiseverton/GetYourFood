package br.com.getyourfood.product;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> listAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/search/{searchText}")
    public List<Product> searchProducts(@PathVariable("searchText") String searchText) {
        return repository.searchByName(searchText.toLowerCase());
    }

    @GetMapping("/{productId}")
    public Product findById(@PathVariable("productId") Long productId) {
        return repository.findById(productId).get();
    }
}
