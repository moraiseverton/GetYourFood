package br.com.getyourfood.store;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.getyourfood.product.Product;
import br.com.getyourfood.product.ProductRepository;

@RestController
@RequestMapping(value = "/Store", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreController {

    private StoreRepository storeRepository;
    private ProductRepository productRepository;

    public StoreController(StoreRepository repository, ProductRepository productRepository) {
        this.storeRepository = repository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Store> listAllStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/search/{searchText}")
    public List<Store> searchStores(@PathVariable("searchText") String searchText) {
       return storeRepository.searchByName(searchText);
    }

    @GetMapping("/{storeId}")
    public Store findById(@PathVariable("storeId") Long storeId) {
        return storeRepository.findById(storeId).get();
    }

    @GetMapping("/{storeId}/products")
    public List<Product> findProductsByStoreId(@PathVariable("storeId") Long storeId) {
        return productRepository.findAllByStoreId(storeId);
    }

}
