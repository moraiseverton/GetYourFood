
package br.com.getyourfood.cousine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CousineRepository extends JpaRepository<Cousine, Long> {

    @Query("select c from Cousine c where lower(c.name) like %:searchText% order by lower(c.name) ")
    List<Cousine> searchByText(@Param("searchText") String searchText);

}
