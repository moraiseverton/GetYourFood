package br.com.getyourfood.cousine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.getyourfood.store.Store;
import br.com.getyourfood.store.StoreRepository;

@Service
public class CousineService {

    @Autowired
    private CousineRepository cousineRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<Cousine> listAllCousines() {
        return cousineRepository.findAll();
    }

    public List<Cousine> searchCousinesByText(String searchText) {
        return cousineRepository.searchByText(searchText.toLowerCase());
    }

    public List<Store> findStoresByCousineId(Long cousineId) {
        return storeRepository.findAllByCousineId(cousineId);
    }
}
