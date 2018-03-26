package br.com.getyourfood.cousine;

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
import br.com.getyourfood.store.Store;

public class CousineControllerTest extends BaseControllerTest {

    @MockBean
    private CousineController controller;

    @Test
    public void listAllCousines() throws Exception {
        Cousine brazilian = new Cousine("Brazilian");
        brazilian.setId(1l);

        Cousine japanese = new Cousine("Japanese");
        japanese.setId(2l);

        List<Cousine> allCousines = Arrays.asList(brazilian, japanese);

        given(controller.listAllCousines()).willReturn(allCousines);

        mockMvc.perform(get("/Cousine").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(allCousines.size())))
                .andExpect(jsonPath("$[0].id", is(brazilian.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(brazilian.getName())))

                .andExpect(jsonPath("$[1].id", is(japanese.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(japanese.getName())));
    }

    @Test
    public void searchSpecificCousines() throws Exception {
        Cousine brazilian = new Cousine("Brazilian cousine");
        brazilian.setId(1l);

        Cousine japanese = new Cousine("Japanese");
        japanese.setId(2l);

        Cousine canadian = new Cousine("Canadian cousine");
        canadian.setId(3l);

        String searchText = "ian";

        given(controller.searchCousines(searchText)).willReturn(Arrays.asList(brazilian, canadian));

        mockMvc.perform(get("/Cousine/search/{searchText}", searchText)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(brazilian.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(brazilian.getName())))

                .andExpect(jsonPath("$[1].id", is(canadian.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(canadian.getName())));
    }

    @Test
    public void findCousinesByCousineId() throws Exception {
        Cousine canadian = new Cousine("Canadian");
        canadian.setId(2l);

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

        List<Store> allStoresFromCanadianCousine = Arrays.asList(store2, store3);

        given(controller.findStoresByCousineId(canadian.getId())).willReturn(allStoresFromCanadianCousine);

        mockMvc.perform(get("/Cousine/{cousineId}/stores", canadian.getId()).contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(allStoresFromCanadianCousine.size())))

                .andExpect(jsonPath("$[0].id", is(store2.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(store2.getName())))
                .andExpect(jsonPath("$[0].address", is(store2.getAddress())))
                .andExpect(jsonPath("$[0].cousineId", is(store2.getCousine().getId().intValue())))

                .andExpect(jsonPath("$[1].id", is(store3.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(store3.getName())))
                .andExpect(jsonPath("$[1].address", is(store3.getAddress())))
                .andExpect(jsonPath("$[1].cousineId", is(store3.getCousine().getId().intValue())));
    }
}
