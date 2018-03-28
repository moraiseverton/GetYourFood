package br.com.getyourfood;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.getyourfood.cousine.Cousine;
import br.com.getyourfood.cousine.CousineRepository;
import br.com.getyourfood.product.Product;
import br.com.getyourfood.product.ProductRepository;
import br.com.getyourfood.store.Store;
import br.com.getyourfood.store.StoreRepository;

@SpringBootApplication
public class GetYourFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetYourFoodApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CousineRepository cousineRepository, StoreRepository storeRepository, ProductRepository productRepository) {
        return args -> {
            initCousines(cousineRepository);
            initStores(storeRepository, cousineRepository);
            initProducts(productRepository, storeRepository);
        };
    }

    private void initProducts(ProductRepository productRepository, StoreRepository storeRepository) throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper mapper2 = new ObjectMapper();
        List<Product> products = mapper2.readValue(new File("./src/main/resources/static/product.json"), new TypeReference<List<Product>>() {});

        products.forEach(product -> {
            Store store = storeRepository.findById(product.getStoreId()).get();
            product.setStore(store);
            productRepository.save(product);
        });
    }

    private void initStores(StoreRepository storeRepository, CousineRepository cousineRepository) throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Store> stores = mapper.readValue(new File("./src/main/resources/static/store.json"), new TypeReference<List<Store>>() {});

        stores.forEach(store -> {
            Cousine cousine = cousineRepository.findById(store.getCousineId()).get();
            store.setCousine(cousine);
            storeRepository.save(store);
        });
    }

    private void initCousines(CousineRepository cousineRepository) throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Cousine> cousines = mapper.readValue(new File("./src/main/resources/static/cousine.json"), new TypeReference<List<Cousine>>() {});

        cousines.forEach(cousine -> {
            cousineRepository.save(cousine);
        });
    }
}
