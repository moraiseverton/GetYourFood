package br.com.getyourfood.cousine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.com.getyourfood.base.BaseRepositoryTest;

public class CousineRepositoryTest extends BaseRepositoryTest {

    @Test
    public void whenSearchCousinesBySearchTextThenReturnAllMatches() {
        Cousine canadian = new Cousine("Canadian cousine");
        entityManager.persist(canadian);
        entityManager.flush();

        Cousine brazilian = new Cousine("Brazilian");
        entityManager.persist(brazilian);
        entityManager.flush();

        Cousine japanese = new Cousine("Japanese");
        entityManager.persist(japanese);
        entityManager.flush();

        List<Cousine> cousines = cousineRepository.searchByText("ian");

        assertThat(cousines.size()).isEqualTo(2);
        assertThat(cousines.get(0)).isEqualTo(brazilian);
        assertThat(cousines.get(1)).isEqualTo(canadian);
    }
}
