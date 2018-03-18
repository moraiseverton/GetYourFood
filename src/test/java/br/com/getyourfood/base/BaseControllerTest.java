package br.com.getyourfood.base;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.getyourfood.GetYourFoodApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GetYourFoodApplication.class)
@WebAppConfiguration
public abstract class BaseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public final void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
}
