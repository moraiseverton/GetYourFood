package br.com.getyourfood.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.com.getyourfood.base.BaseRepositoryTest;
import br.com.getyourfood.cousine.Cousine;

public class StoreRepositoryTest extends BaseRepositoryTest {

    @Test
    public void whenSearchStoresBySearchTextThenReturnAllMatches() {
        Cousine canadian = new Cousine("Canadian cousine");
        entityManager.persist(canadian);
        entityManager.flush();

        Cousine brazilian = new Cousine("Brazilian");
        entityManager.persist(brazilian);
        entityManager.flush();

        Store store1;
        store1 = new Store();
        store1.setName("Za Pizza Bistro");
        store1.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
        store1.setCousine(canadian);
        entityManager.persist(store1);
        entityManager.flush();

        Store store2 = new Store();
        store2.setName("House of poutine");
        store2.setAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada");
        store2.setCousine(canadian);
        entityManager.persist(store2);
        entityManager.flush();

        Store store3 = new Store();
        store3.setName("Fazano Pizza Restaurant");
        store3.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        store3.setCousine(brazilian);
        entityManager.persist(store3);
        entityManager.flush();

        List<Store> stores = storeRepository.searchByName("pizza");

        assertThat(stores.size()).isEqualTo(2);
        assertThat(stores.get(0)).isEqualTo(store3);
        assertThat(stores.get(1)).isEqualTo(store1);
    }

    @Test
    public void whenSearchStoresByCousineIdThenReturnAllMatches() {
        Cousine canadian = new Cousine("Canadian cousine");
        entityManager.persist(canadian);
        entityManager.flush();

        Cousine brazilian = new Cousine("Brazilian");
        entityManager.persist(brazilian);
        entityManager.flush();

        Store store1;
        store1 = new Store();
        store1.setName("Za Pizza Bistro");
        store1.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
        store1.setCousine(canadian);
        entityManager.persist(store1);
        entityManager.flush();

        Store store2 = new Store();
        store2.setName("House of poutine");
        store2.setAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada");
        store2.setCousine(canadian);
        entityManager.persist(store2);
        entityManager.flush();

        Store store3 = new Store();
        store3.setName("Fazano Pizza Restaurant");
        store3.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        store3.setCousine(brazilian);
        entityManager.persist(store3);
        entityManager.flush();

        List<Store> stores = storeRepository.findAllByCousineId(canadian.getId());

        assertThat(stores.size()).isEqualTo(2);
        assertThat(stores.get(0)).isEqualTo(store2);
        assertThat(stores.get(1)).isEqualTo(store1);
    }

}
