package br.com.getyourfood.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where lower(c.email) like %:email% ")
    Customer findByEmail(@Param("email") String email);

}
