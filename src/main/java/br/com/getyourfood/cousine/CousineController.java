package br.com.getyourfood.cousine;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.getyourfood.store.Store;
import br.com.getyourfood.store.StoreRepository;

@RestController
@RequestMapping(value = "/Cousine", produces = MediaType.APPLICATION_JSON_VALUE)
public class CousineController {

    private CousineRepository cousineRepository;

    private StoreRepository storeRepository;

    public CousineController(CousineRepository repository, StoreRepository storeRepository) {
        this.cousineRepository = repository;
        this.storeRepository = storeRepository;
    }

    @GetMapping
    public List<Cousine> listAllCousines() {
        return cousineRepository.findAll();
    }

    @GetMapping("/search/{searchText}")
    public List<Cousine> searchCousines(@PathVariable("searchText") String searchText) {
        return cousineRepository.searchByText(searchText.toLowerCase());
    }

    @GetMapping("/{cousineId}/stores")
    public List<Store> findStoresByCousineId(@PathVariable("cousineId") Long cousineId) {
        return storeRepository.findAllByCousineId(cousineId);
    }
}
