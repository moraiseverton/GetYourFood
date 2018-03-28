package br.com.getyourfood.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.getyourfood.product.Product;

@RestController
@RequestMapping(value = "/Store", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreController {

    @Autowired
    private StoreService storeRepository;

    @GetMapping
    public List<Store> listAllStores() {
        return storeRepository.listAllStores();
    }

    @GetMapping("/search/{searchText}")
    public List<Store> searchStores(@PathVariable("searchText") String searchText) {
       return storeRepository.searchStoresByName(searchText);
    }

    @GetMapping("/{storeId}")
    public Store findById(@PathVariable("storeId") Long storeId) {
        return storeRepository.findStoreById(storeId);
    }

    @GetMapping("/{storeId}/products")
    public List<Product> findProductsByStoreId(@PathVariable("storeId") Long storeId) {
        return storeRepository.findProductsByStoreId(storeId);
    }
}
