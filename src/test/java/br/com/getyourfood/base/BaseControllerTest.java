package br.com.getyourfood.base;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.getyourfood.GetYourFoodApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GetYourFoodApplication.class)
@WebAppConfiguration
@EnableWebMvc
public abstract class BaseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public final void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
}
