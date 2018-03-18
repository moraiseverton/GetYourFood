package br.com.getyourfood.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where lower(p.name) like %:searchText% or lower(p.description) like %:searchText% order by lower(p.name) ")
    List<Product> searchByName(@Param("searchText") String searchText);

    @Query("select p from Product p where p.storeId = :storeId order by lower(p.name) ")
    List<Product> findAllByStoreId(@Param("storeId") Long storeId);

}
