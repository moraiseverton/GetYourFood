package br.com.getyourfood.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.com.getyourfood.base.BaseRepositoryTest;
import br.com.getyourfood.cousine.Cousine;
import br.com.getyourfood.store.Store;

public class ProductRepositoryTest extends BaseRepositoryTest {

    @Test
    public void whenSearchProductsBySearchTextThenReturnAllMatches() {
        Cousine japanese = new Cousine("Canadian cousine");
        entityManager.persist(japanese);
        entityManager.flush();

        Store fazano = new Store();
        fazano.setName("Fazano Restaurant");
        fazano.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        fazano.setCousine(japanese);
        entityManager.persist(fazano);
        entityManager.flush();

        Product product1 = new Product();
        product1.setName("Brazilian Sushi");
        product1.setStore(fazano);
        entityManager.persist(product1);
        entityManager.flush();

        Product product2 = new Product();
        product2.setName("vegan pizza");
        product2.setDescription("delicious vegan pizza of sushi. yummy!");
        product2.setStore(fazano);
        entityManager.persist(product2);
        entityManager.flush();

        Product product3 = new Product();
        product3.setName("amazing vegan sushi");
        product3.setStore(fazano);
        entityManager.persist(product3);
        entityManager.flush();

        Product product4 = new Product();
        product4.setName("fruit soup");
        product4.setStore(fazano);
        entityManager.persist(product4);
        entityManager.flush();

        List<Product> stores = productRepository.searchByName("sushi");

        assertThat(stores.size()).isEqualTo(3);
        assertThat(stores.get(0)).isEqualTo(product3);
        assertThat(stores.get(1)).isEqualTo(product1);
        assertThat(stores.get(2)).isEqualTo(product2);
    }

    @Test
    public void whenSearchProductsByStoreIdThenReturnAllMatches() {
        Cousine brazilian = new Cousine("Brazilian");
        entityManager.persist(brazilian);
        entityManager.flush();

        Store fazano = new Store();
        fazano.setName("Fazano Pizza Restaurant");
        fazano.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        fazano.setCousine(brazilian);
        entityManager.persist(fazano);
        entityManager.flush();

        Store salGastronomia = new Store();
        salGastronomia.setName("Sal Gastronomia");
        salGastronomia.setAddress("1550 Consolacao St., Sao Paulo, Sao Paulo, Brazil");
        salGastronomia.setCousine(brazilian);
        entityManager.persist(salGastronomia);
        entityManager.flush();

        Product product1 = new Product();
        product1.setName("Brazilian Sushi");
        product1.setStore(fazano);
        entityManager.persist(product1);
        entityManager.flush();

        Product product2 = new Product();
        product2.setName("vegan pizza");
        product2.setDescription("delicious vegan pizza of sushi. yummy!");
        product2.setStore(fazano);
        entityManager.persist(product2);
        entityManager.flush();

        Product product3 = new Product();
        product3.setName("amazing vegan sushi");
        product3.setStore(salGastronomia);
        entityManager.persist(product3);
        entityManager.flush();

        Product product4 = new Product();
        product4.setName("fruit soup");
        product4.setStore(fazano);
        entityManager.persist(product4);
        entityManager.flush();

        List<Product> products = productRepository.findAllByStoreId(fazano.getId());

        assertThat(products.size()).isEqualTo(3);
        assertThat(products.get(0)).isEqualTo(product1);
        assertThat(products.get(1)).isEqualTo(product4);
        assertThat(products.get(2)).isEqualTo(product2);
    }
}
