package be.fooda.backend.order.dao;

import be.fooda.backend.order.model.entity.FoodaOrderCustomer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Qualifier("customerRepository")
public interface FoodaOrderCustomerRepository extends JpaRepository<FoodaOrderCustomer, Long>,
                                                  JpaSpecificationExecutor<FoodaOrderCustomer> {
    @Modifying
    @Query("UPDATE FoodaOrderCustomer foc SET foc.order.id= :orderId WHERE foc.id = :id ")
    void updateOrderId(@Param("orderId") Long orderId, @Param("id") Long id);

    @Query("SELECT foc FROM FoodaOrderCustomer foc WHERE foc.order.id = :orderId")
    List<FoodaOrderCustomer> findByOrderId(@Param("orderId") Long orderId);

}
