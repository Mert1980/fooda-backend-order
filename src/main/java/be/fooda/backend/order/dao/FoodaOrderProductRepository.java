package be.fooda.backend.order.dao;

import be.fooda.backend.order.model.entity.FoodaOrderProduct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Qualifier("productRepository")
public interface FoodaOrderProductRepository extends JpaRepository<FoodaOrderProduct, Long>,
                                                     JpaSpecificationExecutor<FoodaOrderProduct> {

    @Modifying
    @Query("UPDATE FoodaOrderProduct op SET op.order.id= :orderId WHERE op.id = :id ")
    void updateOrderId(@Param("orderId") Long orderId, @Param("id") Long id);

    @Query("SELECT fop FROM FoodaOrderProduct fop WHERE fop.order.id = :orderId")
    List<FoodaOrderProduct> findByOrderId(@Param("orderId") Long orderId);
}
