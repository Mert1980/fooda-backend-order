package be.fooda.backend.order.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodaOrderHttpSuccessMessages {

    ORDER_EXISTS("Order exists"),
    ORDER_CREATED("Order is CREATED  based on the given information"),
    ORDER_UPDATED("Order is successfully updated"),
    ORDER_DELETED("Order is deleted"),
    ORDER_STATUS_UPDATED("Order status is updated"),
    ORDER_PAYMENT_STATUS_UPDATED("Order payment status is updated"),
    ORDERS_DELETED("Orders are deleted");

    private final String description;
}
