package br.com.getyourfood;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.getyourfood.cousine.Cousine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetYourFoodApplicationTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void parseJsonCollectionToObjectFromFile() throws JsonParseException, JsonMappingException, IOException {
        File cousineJsonFile = new File("./src/main/resources/static/cousine.json");

        ObjectMapper mapper = new ObjectMapper();
        List<Cousine> cousines = mapper.readValue(cousineJsonFile, new TypeReference<List<Cousine>>() {});

        assertEquals(4, cousines.size());
    }
}
