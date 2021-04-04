package be.fooda.backend.order.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodaOrderHttpFailureMessages {

    SOME_MESSAGE(""),
    ORDER_ALREADY_EXIST("This order already exists."),
    ORDER_DOES_NOT_EXIST("This order does not exist"),
    ORDER_STATUS_NOT_EXIST("This order status does not exist"),
    ORDER_PAYMENT_STATUS_NOT_EXIST("This order payment status does not exist"),
    CUSTOMER_DOES_NOT_EXIST("Customer does not exist"),
    STORE_DOES_NOT_EXIST("Store does not exist"),
    MISSING_PRODUCT_SET("Products do not exist"),
    DELIVERY_DOES_NOT_EXIST("Delivery does not exist"),
    MISSING_PAYMENT_SET("One (of more) payment(s) missed"),
    NO_CANCELED_ORDERS_FOUND("No canceled orders found"),
    NO_COMPLETED_ORDERS_FOUND("No completed orders found"),
    NO_ORDERS_FOUND("No orders found"),
    ORDER_COULD_NOT_BE_DELETED("Order could not be deleted"),
    ORDERS_COULD_NOT_BE_DELETED("Orders could not be deleted"),
    UNKNOWN_FAILURE("");
    private final String description;
}
