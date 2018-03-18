package br.com.getyourfood.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.getyourfood.GetYourFoodApplication;
import br.com.getyourfood.cousine.CousineRepository;
import br.com.getyourfood.product.ProductRepository;
import br.com.getyourfood.store.StoreRepository;

@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = GetYourFoodApplication.class)
public abstract class BaseRepositoryTest {

    @Autowired
    protected CousineRepository cousineRepository;

    @Autowired
    protected StoreRepository storeRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected TestEntityManager entityManager;

    @Before
    public void setup() throws Exception {
        this.productRepository.deleteAllInBatch();
        this.storeRepository.deleteAllInBatch();
        this.cousineRepository.deleteAllInBatch();
    }
}
