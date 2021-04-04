package be.fooda.backend.order.dao;

import be.fooda.backend.commons.model.order.FoodaOrderStatus;
import be.fooda.backend.order.model.entity.FoodaOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Qualifier("orderRepository")
public interface FoodaOrderRepository extends JpaRepository<FoodaOrder, Long>,
        JpaSpecificationExecutor<FoodaOrder> {

    Optional<FoodaOrder> findByIsActiveTrueAndId(Long id);

    List<FoodaOrder> findByCustomer_ExternalCustomerIdAndIsActive(Long externalCustomerId, Boolean isActive);

    List<FoodaOrder> findByCustomer_PhoneAndIsActive(String phone, Boolean isActive);

    List<FoodaOrder> findByCustomer_ExternalCustomerId(Long externalCustomerId);

    List<FoodaOrder> findByCustomer_FirstNameAndCustomer_FamilyNameAndIsActive(String firstName, String familyName, Boolean isActive);

    List<FoodaOrder> findByOrderTrackingIdAndIsActive(Long orderTrackingId, Boolean isActive);

    List<FoodaOrder> findByStatusAndIsActive(FoodaOrderStatus status, Boolean isActive);

    List<FoodaOrder> findByRequiredTimeAndIsActive(LocalTime requiredTime, Boolean isActive);

    List<FoodaOrder> findByRequiredDateAndIsActive(LocalDate requiredDate, Boolean isActive);

    List<FoodaOrder> findByDeliveryTimeAndIsActive(LocalTime deliveryTime, Boolean isActive);

    List<FoodaOrder> findByDeliveryDateAndIsActive(LocalDate deliveryDate, Boolean isActive);

    List<FoodaOrder> findByPaymentDateTimeAndIsActive(LocalDateTime paymentDateTime, Boolean isActive);

    @Query("SELECT o FROM FoodaOrder o WHERE o.isActive=?1 AND o.customer.externalCustomerId = ?2 AND o.id IN (SELECT p.order.id FROM FoodaOrderPayment p WHERE p.externalPaymentItemId = ?3)")
    List<FoodaOrder> findByPaymentAndIsActive( Boolean isActive, Long externalCustomerId, Long externalPaymentItemId);

    @Query("SELECT o FROM FoodaOrder o WHERE o.isActive=?1 AND o.id IN (SELECT p.order.id FROM FoodaOrderPayment p WHERE p.amount >= ?2)")
    List<FoodaOrder> findByPriceMinAndIsActive( Boolean isActive, BigDecimal amount);

    @Query("SELECT o FROM FoodaOrder o WHERE o.isActive=?1 AND o.id IN (SELECT p.order.id FROM FoodaOrderPayment p WHERE p.amount >= ?1 AND p.amount <= ?2)")
    List<FoodaOrder> findByPriceRangeAndIsActive( Boolean isActive, BigDecimal minAmount, BigDecimal maxAmount);

    List<FoodaOrder> findByIsActiveTrueAndStatusNotIn( List<FoodaOrderStatus> statuses);

    List<FoodaOrder> findByStatusAndIsActiveTrue( FoodaOrderStatus status);


    List<FoodaOrder> findByStore_ExternalStoreIdAndIsActive(Long externalStoreId, Boolean isActive);

    void deleteByOrderTrackingIdAndStore_ExternalStoreId(Long orderTrackingId, Long externalStoreId);

    void deleteByCustomer_ExternalCustomerId(Long id);

    @Query("SELECT o FROM FoodaOrder o WHERE o.isActive = :isActive")
    Page<FoodaOrder> findAll(@Param("isActive")boolean isActive, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM FoodaOrder o WHERE o.customer.externalCustomerId = :externalCustomerId AND o.orderTrackingId = :orderTrackingId AND o.requiredDate = :requiredDate AND o.requiredTime = :requiredTime")
    boolean existByUniqueFields(@Param("externalCustomerId") Long externalCustomerId, @Param("orderTrackingId") final Long orderTrackingId, @Param("requiredDate") final LocalDate requiredDate, @Param("requiredTime") final LocalTime requiredTime);

    @Query("SELECT o FROM FoodaOrder o WHERE o.customer.externalCustomerId = :externalCustomerId OR o.orderTrackingId = :orderTrackingId OR (o.requiredDate = :requiredDate AND o.requiredTime = :requiredTime)")
    List<FoodaOrder> findAllByUniqueFields(@Param("externalCustomerId") final Long externalCustomerId, @Param("orderTrackingId") final Long orderTrackingId, @Param("requiredDate") final LocalDate requiredDate, @Param("requiredTime") final LocalTime requiredTime);


}
