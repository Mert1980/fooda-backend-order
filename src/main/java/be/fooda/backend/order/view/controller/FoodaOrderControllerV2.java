package be.fooda.backend.order.view.controller;

import be.fooda.backend.commons.model.order.FoodaOrderStatus;
import be.fooda.backend.commons.model.order.create.FoodaOrderCreate;
import be.fooda.backend.commons.model.order.create.FoodaOrderPaymentCreate;
import be.fooda.backend.commons.model.order.create.FoodaOrderProductCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderExample;
import be.fooda.backend.commons.model.payment.FoodaPaymentStatus;
import be.fooda.backend.order.client.*;
import be.fooda.backend.order.dao.FoodaOrderRepository;
import be.fooda.backend.order.dao.indexing.FoodaOrderIndexRepository;
import be.fooda.backend.order.model.entity.FoodaOrder;
import be.fooda.backend.order.model.http.FoodaOrderHttpFailureMessages;
import be.fooda.backend.order.model.http.FoodaOrderHttpSuccessMessages;
import be.fooda.backend.order.service.mapper.FoodaOrderMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("api/v2/order")
public class FoodaOrderControllerV2 {

    private final FoodaOrderRepository repository;
    private final FoodaOrderMapper mapper;
    private final FoodaOrderIndexRepository indexRepository;

    private final FoodaCustomerClient customerClient;
    private final FoodaStoreClient storeClient;
    private final FoodaProductClient productClient;
    private final FoodaDeliveryClient deliveryClient;
    private final FoodaPaymentClient paymentClient;
    private final FoodaBasketClient basketClient;
    private final FoodaTwilioClient twilioClient;


    @GetMapping("get_all_orders")
    public ResponseEntity getAllOrders(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize,
                                       @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);

        Page<FoodaOrder> pageOrders = repository.findAll(isActive, paging);
        if (!pageOrders.hasContent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(pageOrders.getContent());
    }

    @GetMapping("get_order_by_id")
    public ResponseEntity getOrderById(@RequestParam @Positive Long id,
                                       @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final Optional<FoodaOrder> foundOrder = repository.findById(id);
        if (!foundOrder.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);

        if (!foundOrder.get().getIsActive().equals(isActive))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrder.get());
    }

    @GetMapping("get_orders_by_status")
    public ResponseEntity getOrdersByStatus(@RequestParam String status,
                                            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        if (Arrays.stream(FoodaOrderStatus.values()).anyMatch(anyStatus -> anyStatus.toString().equalsIgnoreCase(status))) {
            return new ResponseEntity(repository.findByStatusAndIsActive(FoodaOrderStatus.valueOf(status.toUpperCase()), isActive), HttpStatus.FOUND);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_STATUS_NOT_EXIST);
    }

    @GetMapping("get_recent_orders")
    public ResponseEntity getRecentOrders() {
        final List<FoodaOrder> foundOrders = repository.findByIsActiveTrueAndStatusNotIn(List.of(FoodaOrderStatus.CANCELED, FoodaOrderStatus.COMPLETED));
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return new ResponseEntity(foundOrders, HttpStatus.FOUND);
    }

    @GetMapping("get_completed_orders")
    public ResponseEntity getCompletedOrders() {
        final List<FoodaOrder> foundOrders = repository.findByStatusAndIsActiveTrue(FoodaOrderStatus.COMPLETED);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_COMPLETED_ORDERS_FOUND);

        return new ResponseEntity(foundOrders, HttpStatus.FOUND);
    }

    @GetMapping("get_canceled_orders")
    public ResponseEntity getCanceledOrders() {
        final List<FoodaOrder> foundOrders = repository.findByStatusAndIsActiveTrue(FoodaOrderStatus.CANCELED);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_CANCELED_ORDERS_FOUND);

        return new ResponseEntity(foundOrders, HttpStatus.FOUND);
    }

    @GetMapping("get_all_order_statuses")
    public ResponseEntity getAllOrderStatuses() {
        return ResponseEntity.status(HttpStatus.FOUND).body(Arrays.asList(FoodaOrderStatus.values().clone()));
    }

    @GetMapping("get_by_required_time")
    public ResponseEntity getByRequiredTime(@RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime requiredTime,
                                            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByRequiredTimeAndIsActive(requiredTime, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_required_date")
    public ResponseEntity getByRequiredDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate requiredDate,
                                            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByRequiredDateAndIsActive(requiredDate, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }


    @GetMapping("get_by_delivery_time")
    public ResponseEntity getByDeliveryTime(@RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime deliveryTime,
                                            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByDeliveryTimeAndIsActive(deliveryTime, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_delivery_date")
    public ResponseEntity getByDeliveryDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deliveryDate,
                                            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByDeliveryDateAndIsActive(deliveryDate, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_payment_date_time")
    public ResponseEntity getByPaymentDateTime(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime paymentDateTime,
                                               @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByPaymentDateTimeAndIsActive(paymentDateTime, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_payment_id")
    public ResponseEntity getByPaymentId(@RequestParam @PositiveOrZero Long externalCustomerId,
                                         @RequestParam @PositiveOrZero Long externalPaymentId,
                                         @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByPaymentAndIsActive(isActive, externalCustomerId, externalPaymentId);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_payment_amount")
    public ResponseEntity getByPaymentAmount(@RequestParam @PositiveOrZero BigDecimal min,
                                             @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByPriceMinAndIsActive(isActive, min);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_payment_range")
    public ResponseEntity getByPaymentRange(@RequestParam @PositiveOrZero BigDecimal min,
                                            @RequestParam @PositiveOrZero BigDecimal max,
                                            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByPriceRangeAndIsActive(isActive, min, max);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_store_id")
    public ResponseEntity getByStoreId(@RequestParam @PositiveOrZero Long externalStoreId,
                                       @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByStore_ExternalStoreIdAndIsActive(externalStoreId, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_customer_id")
    public ResponseEntity getByCustomerId(@RequestParam @PositiveOrZero Long externalCustomerId,
                                          @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByCustomer_ExternalCustomerIdAndIsActive(externalCustomerId, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("get_by_customer_phone")
    public ResponseEntity getByCustomerPhone(@RequestParam @PositiveOrZero String phone,
                                          @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = repository.findByCustomer_PhoneAndIsActive(phone, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("search_by_customer")
    public ResponseEntity searchByCustomer(
            @RequestParam(required = false) String firstName, @RequestParam @NotNull @Length(min = 1) String familyName,
            @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {

        final List<FoodaOrder> foundOrders = indexRepository.searchByCustomerFullName(firstName, familyName, pageNo - 1, pageSize, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("search_by_store")
    public ResponseEntity searchByStore(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {

        final List<FoodaOrder> foundOrders = indexRepository.searchByStoreName(storeName, pageNo - 1, pageSize, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    @GetMapping("search_by_product")
    public ResponseEntity searchByProduct(
            @RequestParam String productName,
            @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "true", required = false) Boolean isActive) {

        final List<FoodaOrder> foundOrders = indexRepository.searchByProductName(productName, pageNo - 1, pageSize, isActive);
        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }

    private ResponseEntity allExternalIdsExist(FoodaOrderCreate order) {
        if (!customerClient.exist(order.getCustomer().getExternalCustomerId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.CUSTOMER_DOES_NOT_EXIST);
        }

        if (!storeClient.exist(order.getStore().getExternalStoreId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.STORE_DOES_NOT_EXIST);
        }

        if (!productClient.exist(order.getProducts().stream().map(FoodaOrderProductCreate::getExternalProductId).collect(Collectors.toSet()))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.STORE_DOES_NOT_EXIST);
        }

        if (!order.getPayments().isEmpty() &&
                !paymentClient.existByIdSet(order.getPayments().stream().map(FoodaOrderPaymentCreate::getExternalPaymentItemId).collect(Collectors.toSet()))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.MISSING_PAYMENT_SET);
        }

        if (!deliveryClient.exist(order.getDelivery().getExternalDeliveryId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.DELIVERY_DOES_NOT_EXIST);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("create_order")
    public ResponseEntity create(@RequestBody @Valid FoodaOrderCreate order) {
        final boolean existByUniqueFields = repository.existByUniqueFields(order.getCustomer().getExternalCustomerId(), order.getOrderTrackingId(), order.getRequiredDate(), order.getRequiredTime());
        if (existByUniqueFields)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(FoodaOrderHttpFailureMessages.ORDER_ALREADY_EXIST);

        ResponseEntity checkResponse = allExternalIdsExist(order);
        if (!checkResponse.getStatusCode().equals(HttpStatus.OK))
            return checkResponse;

        final FoodaOrder orderBeingSaved = mapper.fromCreateToEntity(order);
        repository.save(orderBeingSaved);
        twilioClient.sendSmsToCustomer(orderBeingSaved.getCustomer().getPhone(), "Your order is successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(FoodaOrderHttpSuccessMessages.ORDER_CREATED);
    }

    @PatchMapping("update_order_status_by_id")
    public ResponseEntity updateOrderStatusById(@RequestParam @PositiveOrZero Long id,
                                                @RequestParam String orderStatus) {

        FoodaOrderStatus foodaOrderStatus = null;
        for (FoodaOrderStatus s : FoodaOrderStatus.values()) {
            if (s.toString().equalsIgnoreCase(orderStatus)) foodaOrderStatus = s;
        }
        if (foodaOrderStatus == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_STATUS_NOT_EXIST);
        final Optional<FoodaOrder> foundOrder = repository.findById(id);
        if (foundOrder.isPresent()) {
            foundOrder.get().setStatus(foodaOrderStatus);
            repository.save(foundOrder.get());
            twilioClient.sendSmsToCustomer(foundOrder.get().getCustomer().getPhone(), "Order status change to: " + foodaOrderStatus.getValue());
            if (foodaOrderStatus.equals(FoodaOrderStatus.COMPLETED) | foodaOrderStatus.equals(FoodaOrderStatus.CANCELED))
                basketClient.deleteBasket();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(FoodaOrderHttpSuccessMessages.ORDER_STATUS_UPDATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);
        }
    }

    @PatchMapping("update_order_payment_status_by_id")
    public ResponseEntity updateOrderPaymentStatusById(@RequestParam @PositiveOrZero Long id,
                                                       @RequestParam String paymentStatus) {

        FoodaPaymentStatus foodaPaymentStatus = null;
        for (FoodaPaymentStatus s : FoodaPaymentStatus.values()) {
            if (s.toString().equalsIgnoreCase(paymentStatus)) foodaPaymentStatus = s;
        }
        if (foodaPaymentStatus == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_PAYMENT_STATUS_NOT_EXIST);
        final Optional<FoodaOrder> foundOrder = repository.findById(id);
        if (foundOrder.isPresent() && foodaPaymentStatus != null) {
            foundOrder.get().setPaymentStatus(foodaPaymentStatus);
            foundOrder.get().setStatus(FoodaOrderStatus.PROCESSING_PAYED);
            repository.save(foundOrder.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(FoodaOrderHttpSuccessMessages.ORDER_PAYMENT_STATUS_UPDATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);
        }
    }

    @PatchMapping("delete_by_customer_id")
    public ResponseEntity deleteByCustomerId(@RequestParam @PositiveOrZero Long id) {
        List<FoodaOrder> activeOrders = repository.findByCustomer_ExternalCustomerId(id)
                .stream()
                .filter(FoodaOrder::getIsActive)
                .collect(Collectors.toList());
        if (!activeOrders.isEmpty()) {
            activeOrders.stream()
                    .map(order -> {
                        order.setIsActive(Boolean.FALSE);
                        repository.save(order);
                        return order;
                    })
                    .collect(Collectors.toList());

            activeOrders = repository.findByCustomer_ExternalCustomerId(id)
                    .stream()
                    .filter(FoodaOrder::getIsActive)
                    .collect(Collectors.toList());
            if (!activeOrders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.MULTI_STATUS)
                        .body(FoodaOrderHttpFailureMessages.ORDERS_COULD_NOT_BE_DELETED + "\n" + activeOrders);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(FoodaOrderHttpSuccessMessages.ORDERS_DELETED);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);
        }
    }

    @PatchMapping("delete_by_id")
    public ResponseEntity deleteById(@RequestParam Long id) {
        Optional<FoodaOrder> foundOrder = repository.findById(id);
        if (foundOrder.isPresent() && foundOrder.get().getIsActive()) {
            FoodaOrder orderBeingDeleted = foundOrder.get();
            orderBeingDeleted.setIsActive(Boolean.FALSE);
            repository.save(orderBeingDeleted);
            if (!repository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(FoodaOrderHttpFailureMessages.ORDER_COULD_NOT_BE_DELETED);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(FoodaOrderHttpSuccessMessages.ORDER_DELETED);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);
        }
    }

    @GetMapping("exists_by_id")
    public ResponseEntity existsById(@RequestParam @PositiveOrZero Long id) {
        if (!repository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);
        return ResponseEntity.status(HttpStatus.FOUND).body(FoodaOrderHttpSuccessMessages.ORDER_EXISTS);
    }

    @PostMapping("exists_by_example")
    public ResponseEntity exists(@RequestBody @Valid FoodaOrderExample example) {
        final List<FoodaOrder> allByUniqueFields = repository.findAllByUniqueFields(example.getExternalCustomerId(), example.getOrderTrackingId(), example.getRequiredDate(), example.getRequiredTime());
        if (allByUniqueFields.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.ORDER_DOES_NOT_EXIST);

        return ResponseEntity.status(HttpStatus.FOUND).body(FoodaOrderHttpSuccessMessages.ORDER_EXISTS);
    }

    @GetMapping("search")
    public ResponseEntity search(@RequestParam Set<String> keyword, @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "25") Integer pageSize,
                                 @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<FoodaOrder> foundOrders = indexRepository.simple(keyword, PageRequest.of(pageNo, pageSize), isActive);

        if (foundOrders.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaOrderHttpFailureMessages.NO_ORDERS_FOUND);
        else return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }
}
