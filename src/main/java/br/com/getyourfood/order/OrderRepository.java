package br.com.getyourfood.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from TB_ORDER o join fetch o.orderItems i where o.customerId = :customerId ")
    List<Order> findAllByCustomerId(@Param("customerId") Long customerId);

}
