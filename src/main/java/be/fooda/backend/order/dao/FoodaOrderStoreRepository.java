package be.fooda.backend.order.dao;

import be.fooda.backend.order.model.entity.FoodaOrderStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Qualifier("storeRepository")
public interface FoodaOrderStoreRepository extends JpaRepository<FoodaOrderStore, Long>,
                                                    JpaSpecificationExecutor<FoodaOrderStore> {
    @Modifying
    @Query("UPDATE FoodaOrderStore op SET op.order.id= :orderId WHERE op.id = :id ")
    void updateOrderId(@Param("orderId") Long orderId, @Param("id") Long id);

    @Query("SELECT fos FROM FoodaOrderStore fos WHERE fos.order.id = :orderId")
    List<FoodaOrderStore> findByOrderId(@Param("orderId") Long orderId);

}
