package be.fooda.backend.order.dao;

import be.fooda.backend.order.model.entity.FoodaOrderPayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Qualifier("paymentRepository")
public interface FoodaOrderPaymentRepository extends JpaRepository<FoodaOrderPayment, Long>,
                                                     JpaSpecificationExecutor<FoodaOrderPayment> {

    @Modifying
    @Query("UPDATE FoodaOrderPayment op SET op.order.id= :orderId WHERE op.id = :id ")
    void updateOrderId(@Param("orderId") Long orderId, @Param("id") Long id);

    @Query("SELECT fop FROM FoodaOrderPayment fop WHERE fop.order.id = :orderId")
    List<FoodaOrderPayment> findByOrderId(@Param("orderId") Long orderId);
}
