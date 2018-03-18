package br.com.getyourfood.product;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.com.getyourfood.base.BaseControllerTest;
import br.com.getyourfood.cousine.Cousine;
import br.com.getyourfood.store.Store;

public class ProductControllerTest extends BaseControllerTest {

    @MockBean
    private ProductController controller;

    @Test
    public void listAllProducts() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Cousine canadian = new Cousine("Canadian");
        canadian.setId(2l);

        Store fazanoPizza = new Store();
        fazanoPizza.setId(99l);
        fazanoPizza.setName("Fazano Restaurant");
        fazanoPizza.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        fazanoPizza.setCousine(brazilian);
        fazanoPizza.setCousineId(brazilian.getId());

        Store houseOfPoutine = new Store();
        houseOfPoutine.setId(88l);
        houseOfPoutine.setName("House of poutine");
        houseOfPoutine.setAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada");
        houseOfPoutine.setCousine(canadian);
        houseOfPoutine.setCousineId(canadian.getId());

        Store zePizzaBistro = new Store();
        zePizzaBistro.setId(77l);
        zePizzaBistro.setName("Za Pizza Bistro");
        zePizzaBistro.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
        zePizzaBistro.setCousine(canadian);
        zePizzaBistro.setCousineId(canadian.getId());

        Product product1 = new Product();
        product1.setId(222l);
        product1.setName("chicken pizza");
        product1.setDescription("delicious chicken pizza!");
        product1.setPrice(16.99);
        product1.setStore(fazanoPizza);
        product1.setStoreId(product1.getStore().getId());

        Product product2 = new Product();
        product2.setId(333l);
        product2.setName("cheese pizza");
        product2.setPrice(11.99);
        product2.setStore(fazanoPizza);
        product2.setStoreId(product2.getStore().getId());

        Product product3 = new Product();
        product3.setId(111l);
        product3.setName("vegan poutine");
        product3.setDescription("brand new poutine vegan ");
        product3.setPrice(11.99);
        product3.setStore(houseOfPoutine);
        product3.setStoreId(product3.getStore().getId());

        Product product4 = new Product();
        product4.setId(444l);
        product4.setName("vegan pizza");
        product4.setDescription("pizza with no meat");
        product4.setPrice(11.99);
        product4.setStore(zePizzaBistro);
        product4.setStoreId(product4.getStore().getId());

        List<Product> allProducts = Arrays.asList(product1, product2, product3, product4);

        given(controller.listAllProducts()).willReturn(allProducts);

        mockMvc.perform(get("/Product").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(allProducts.size())))
                .andExpect(jsonPath("$[0].id", is(product1.getId().intValue())))
                .andExpect(jsonPath("$[0].storeId", is(product1.getStore().getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(product1.getName())))
                .andExpect(jsonPath("$[0].description", is(product1.getDescription())))
                .andExpect(jsonPath("$[0].price", is(product1.getPrice().doubleValue())))

                .andExpect(jsonPath("$[1].id", is(product2.getId().intValue())))
                .andExpect(jsonPath("$[1].storeId", is(product2.getStore().getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(product2.getName())))
                .andExpect(jsonPath("$[1].description", is(product2.getDescription())))
                .andExpect(jsonPath("$[1].price", is(product2.getPrice().doubleValue())))

                .andExpect(jsonPath("$[2].id", is(product3.getId().intValue())))
                .andExpect(jsonPath("$[2].storeId", is(product3.getStore().getId().intValue())))
                .andExpect(jsonPath("$[2].name", is(product3.getName())))
                .andExpect(jsonPath("$[2].description", is(product3.getDescription())))
                .andExpect(jsonPath("$[2].price", is(product3.getPrice().doubleValue())))

                .andExpect(jsonPath("$[3].id", is(product4.getId().intValue())))
                .andExpect(jsonPath("$[3].storeId", is(product4.getStore().getId().intValue())))
                .andExpect(jsonPath("$[3].name", is(product4.getName())))
                .andExpect(jsonPath("$[3].description", is(product4.getDescription())))
                .andExpect(jsonPath("$[3].price", is(product4.getPrice().doubleValue())));
    }

    @Test
    public void searchSpecificProducts() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Store fazano = new Store();
        fazano.setId(99l);
        fazano.setName("Fazano Pizza Restaurant");
        fazano.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        fazano.setCousine(brazilian);
        fazano.setCousineId(brazilian.getId());

        Product product1 = new Product();
        product1.setId(111l);
        product1.setName("vegan poutine");
        product1.setDescription("brand new poutine vegan ");
        product1.setPrice(11.99);
        product1.setStore(fazano);
        product1.setStoreId(product1.getStore().getId());

        Product product2 = new Product();
        product2.setId(444l);
        product2.setName("vegan pizza");
        product2.setDescription("pizza with no meat");
        product2.setPrice(11.99);
        product2.setStore(fazano);
        product2.setStoreId(product2.getStore().getId());

        String searchText = "vegan";

        given(controller.searchProducts(searchText)).willReturn(Arrays.asList(product2, product1));

        mockMvc.perform(get("/Product/search/{searchText}", searchText).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(product2.getId().intValue())))
                .andExpect(jsonPath("$[0].storeId", is(product2.getStore().getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(product2.getName())))
                .andExpect(jsonPath("$[0].description", is(product2.getDescription())))
                .andExpect(jsonPath("$[0].price", is(product2.getPrice().doubleValue())))

                .andExpect(jsonPath("$[1].id", is(product1.getId().intValue())))
                .andExpect(jsonPath("$[1].storeId", is(product1.getStore().getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(product1.getName())))
                .andExpect(jsonPath("$[1].description", is(product1.getDescription())))
                .andExpect(jsonPath("$[1].price", is(product1.getPrice().doubleValue())));
    }

    @Test
    public void findById() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Store fazano = new Store();
        fazano.setId(99l);
        fazano.setName("Fazano Pizza Restaurant");
        fazano.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        fazano.setCousine(brazilian);
        fazano.setCousineId(brazilian.getId());

        Product product2 = new Product();
        product2.setId(111l);
        product2.setName("vegan poutine");
        product2.setDescription("brand new poutine vegan ");
        product2.setPrice(11.99);
        product2.setStore(fazano);
        product2.setStoreId(product2.getStore().getId());

        long productId = 111l;

        given(controller.findById(productId)).willReturn(product2);

        mockMvc.perform(get("/Product/{productId}", productId).contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id", is(product2.getId().intValue())))
                .andExpect(jsonPath("$.storeId", is(product2.getStore().getId().intValue())))
                .andExpect(jsonPath("$.name", is(product2.getName())))
                .andExpect(jsonPath("$.description", is(product2.getDescription())))
                .andExpect(jsonPath("$.price", is(product2.getPrice().doubleValue())));
    }

}
