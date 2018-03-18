package br.com.getyourfood.store;

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
import br.com.getyourfood.product.Product;

public class StoreControllerTest extends BaseControllerTest {

    @MockBean
    private StoreController storeController;

    @Test
    public void listAllStores() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Cousine canadian = new Cousine("Canadian");
        canadian.setId(2l);

        Store store1 = new Store();
        store1.setId(99l);
        store1.setName("Fazano Restaurant");
        store1.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        store1.setCousine(brazilian);
        store1.setCousineId(brazilian.getId());

        Store store2 = new Store();
        store2.setId(88l);
        store2.setName("House of poutine");
        store2.setAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada");
        store2.setCousine(canadian);
        store2.setCousineId(canadian.getId());

        Store store3 = new Store();
        store3.setId(77l);
        store3.setName("Za Pizza Bistro");
        store3.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
        store3.setCousine(canadian);
        store3.setCousineId(canadian.getId());

        List<Store> allStores = Arrays.asList(store1, store2, store3);

        given(storeController.listAllStores()).willReturn(allStores);

        mockMvc.perform(get("/Store").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(allStores.size())))
                .andExpect(jsonPath("$[0].id", is(store1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(store1.getName())))
                .andExpect(jsonPath("$[0].address", is(store1.getAddress())))
                .andExpect(jsonPath("$[0].cousineId", is(store1.getCousine().getId().intValue())))

                .andExpect(jsonPath("$[1].id", is(store2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(store2.getName())))
                .andExpect(jsonPath("$[1].address", is(store2.getAddress())))
                .andExpect(jsonPath("$[1].cousineId", is(store2.getCousine().getId().intValue())))

                .andExpect(jsonPath("$[2].id", is(store3.getId().intValue())))
                .andExpect(jsonPath("$[2].name", is(store3.getName())))
                .andExpect(jsonPath("$[2].address", is(store3.getAddress())))
                .andExpect(jsonPath("$[2].cousineId", is(store3.getCousine().getId().intValue())));
    }

    @Test
    public void searchSpecificStores() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Cousine canadian = new Cousine("Canadian");
        canadian.setId(2l);

        Store store1 = new Store();
        store1.setId(77l);
        store1.setName("Za Pizza Bistro");
        store1.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
        store1.setCousine(canadian);
        store1.setCousineId(canadian.getId());

        Store store2 = new Store();
        store2.setId(99l);
        store2.setName("Fazano Pizza Restaurant");
        store2.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        store2.setCousine(brazilian);
        store2.setCousineId(brazilian.getId());

        String searchText = "pizza";

        given(storeController.searchStores(searchText)).willReturn(Arrays.asList(store2, store1));

        mockMvc.perform(get("/Store/search/{searchText}", searchText).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(store2.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(store2.getName())))
                .andExpect(jsonPath("$[0].address", is(store2.getAddress())))
                .andExpect(jsonPath("$[0].cousineId", is(store2.getCousine().getId().intValue())))

                .andExpect(jsonPath("$[1].id", is(store1.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(store1.getName())))
                .andExpect(jsonPath("$[1].address", is(store1.getAddress())))
                .andExpect(jsonPath("$[1].cousineId", is(store1.getCousine().getId().intValue())));
    }

    @Test
    public void findById() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Store store = new Store();
        store.setId(99l);
        store.setName("Fazano Restaurant");
        store.setAddress("2991 Paulista Ave, Sao Paulo, Sao Paulo, Brazil");
        store.setCousine(brazilian);
        store.setCousineId(brazilian.getId());

        long id = 99l;

        given(storeController.findById(id)).willReturn(store);

        mockMvc.perform(get("/Store/{storeId}", id).contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id", is(store.getId().intValue())))
                .andExpect(jsonPath("$.name", is(store.getName())))
                .andExpect(jsonPath("$.address", is(store.getAddress())))
                .andExpect(jsonPath("$.cousineId", is(store.getCousine().getId().intValue())));
    }

    @Test
    public void findProductsByStoreId() throws Exception {
        Cousine canadian = new Cousine("Canadian");
        canadian.setId(2l);

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
        product1.setStore(zePizzaBistro);
        product1.setStoreId(product1.getStore().getId());

        Product product2 = new Product();
        product2.setId(333l);
        product2.setName("cheese pizza");
        product2.setPrice(11.99);
        product2.setStore(zePizzaBistro);
        product2.setStoreId(product2.getStore().getId());

        Product product4 = new Product();
        product4.setId(444l);
        product4.setName("vegan pizza");
        product4.setDescription("pizza with no meat");
        product4.setPrice(11.99);
        product4.setStore(zePizzaBistro);
        product4.setStoreId(product4.getStore().getId());

        List<Product> allProductsFromZePizzaBistro = Arrays.asList(product2, product1, product4);

        given(storeController.findProductsByStoreId(zePizzaBistro.getId())).willReturn(allProductsFromZePizzaBistro);

        mockMvc.perform(get("/Store/{storeId}/products", zePizzaBistro.getId()).contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(allProductsFromZePizzaBistro.size())))

                .andExpect(jsonPath("$[0].id", is(product2.getId().intValue())))
                .andExpect(jsonPath("$[0].storeId", is(product2.getStore().getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(product2.getName())))
                .andExpect(jsonPath("$[0].description", is(product2.getDescription())))
                .andExpect(jsonPath("$[0].price", is(product2.getPrice().doubleValue())))

                .andExpect(jsonPath("$[1].id", is(product1.getId().intValue())))
                .andExpect(jsonPath("$[1].storeId", is(product1.getStore().getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(product1.getName())))
                .andExpect(jsonPath("$[1].description", is(product1.getDescription())))
                .andExpect(jsonPath("$[1].price", is(product1.getPrice().doubleValue())))

                .andExpect(jsonPath("$[2].id", is(product4.getId().intValue())))
                .andExpect(jsonPath("$[2].storeId", is(product4.getStore().getId().intValue())))
                .andExpect(jsonPath("$[2].name", is(product4.getName())))
                .andExpect(jsonPath("$[2].description", is(product4.getDescription())))
                .andExpect(jsonPath("$[2].price", is(product4.getPrice().doubleValue())));
    }
}
