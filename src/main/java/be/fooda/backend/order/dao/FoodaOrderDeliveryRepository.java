package be.fooda.backend.order.dao;

import be.fooda.backend.order.model.entity.FoodaOrderDelivery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Qualifier("deliveryRepository")
public interface FoodaOrderDeliveryRepository extends JpaRepository<FoodaOrderDelivery, Long>,
        JpaSpecificationExecutor<FoodaOrderDelivery> {
    @Modifying
    @Query("UPDATE FoodaOrderDelivery fod SET fod.order.id= :orderId WHERE fod.id = :id ")
    void updateOrderId(@Param("orderId") Long orderId, @Param("id") Long id);

    @Query("SELECT fod FROM FoodaOrderDelivery fod WHERE fod.order.id = :orderId")
    List<FoodaOrderDelivery> findByOrderId(@Param("orderId") Long orderId);

}
