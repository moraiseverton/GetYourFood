package br.com.getyourfood.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.getyourfood.product.Product;
import br.com.getyourfood.product.ProductRepository;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Store> listAllStores() {
        return storeRepository.findAll();
    }

    public List<Store> searchStoresByName(String searchText) {
       return storeRepository.searchByName(searchText.toLowerCase());
    }

    public Store findStoreById(Long storeId) {
        return storeRepository.findById(storeId).get();
    }

    public List<Product> findProductsByStoreId(Long storeId) {
        return productRepository.findAllByStoreId(storeId);
    }
}
