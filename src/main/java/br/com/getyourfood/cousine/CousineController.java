package br.com.getyourfood.cousine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.getyourfood.store.Store;

@RestController
@RequestMapping(value = "/Cousine", produces = MediaType.APPLICATION_JSON_VALUE)
public class CousineController {

    @Autowired
    private CousineService service;

    @GetMapping
    public List<Cousine> listAllCousines() {
        return service.listAllCousines();
    }

    @GetMapping("/search/{searchText}")
    public List<Cousine> searchCousines(@PathVariable("searchText") String searchText) {
        return service.searchCousinesByText(searchText);
    }

    @GetMapping("/{cousineId}/stores")
    public List<Store> findStoresByCousineId(@PathVariable("cousineId") Long cousineId) {
        return service.findStoresByCousineId(cousineId);
    }
}
