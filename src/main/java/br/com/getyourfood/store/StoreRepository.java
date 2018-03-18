package br.com.getyourfood.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s from Store s where lower(s.name) like %:searchText% order by lower(s.name) ")
    List<Store> searchByName(@Param("searchText") String searchText);

    @Query("select s from Store s where s.cousineId = :cousineId order by lower(s.name) ")
    List<Store> findAllByCousineId(@Param("cousineId") Long cousineId);

}
