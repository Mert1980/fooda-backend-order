package be.fooda.backend.order.model.entity;

import be.fooda.backend.commons.model.order.FoodaOrderStatus;
import be.fooda.backend.commons.model.payment.FoodaPaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@Entity
@Indexed
public class FoodaOrder {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;

    private Long orderTrackingId;

    @Field
    private Boolean isActive = Boolean.TRUE;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @IndexedEmbedded
    @JsonIgnoreProperties("order")
    private FoodaOrderDelivery delivery;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @IndexedEmbedded
    @JsonIgnoreProperties("order")
    private FoodaOrderCustomer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @IndexedEmbedded
    @JsonIgnoreProperties("order")
    private FoodaOrderStore store;

    @Field
    @Enumerated(EnumType.STRING)
    private FoodaOrderStatus status = FoodaOrderStatus.PROCESSING_PARENT;

    @Field
    @Enumerated(EnumType.STRING)
    private FoodaPaymentStatus paymentStatus = FoodaPaymentStatus.NOT_PAID;

    @Lob
    @Field
    private String note;

    @Field
    @SortableField
    private LocalTime requiredTime;

    @Field
    @SortableField
    private LocalDate requiredDate;

    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    @Field
    @SortableField
    private LocalTime deliveryTime;

    @Field
    @SortableField
    private LocalDate deliveryDate;

    @Field
    @SortableField
    private LocalDateTime paymentDateTime;

    @Column(columnDefinition = "DECIMAL(8,2) default 0")
    @Min(value = 0)
    private BigDecimal productsTotal;

    @Column(columnDefinition = "DECIMAL(8,2) default 0")
    @Min(value = 0)
    private BigDecimal taxTotal;

    @Column(columnDefinition = "DECIMAL(8,2) default 0")
    @Min(value = 0)
    @Field
    @SortableField
    private BigDecimal deliveryTotal;

    @Column(columnDefinition = "DECIMAL(8,2) default 0")
    @Min(value = 0)
    @Field
    @SortableField
    private BigDecimal priceTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @IndexedEmbedded
    @JsonIgnoreProperties("order")
    private List<FoodaOrderProduct> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @IndexedEmbedded
    @JsonIgnoreProperties("order")
    private List<FoodaOrderPayment> payments;

    public void setCustomer(FoodaOrderCustomer customer) {
        customer.setOrder(this);
        this.customer = customer;
    }

    public void setStore(FoodaOrderStore store) {
        store.setOrder(this);
        this.store = store;
    }

    public void setDelivery(FoodaOrderDelivery delivery) {

        delivery.setOrder(this);
        this.delivery = delivery;
    }

    private FoodaOrderProduct setOneProduct(FoodaOrderProduct product) {
        product.setOrder(this);
        return product;
    }

    public void setProducts(List<FoodaOrderProduct> products) {
        if(products == null) this.products = new ArrayList<>();
        this.products = products.stream()
                .map(this::setOneProduct)
                .collect(Collectors.toList());
    }

    private FoodaOrderPayment setOnePayment(FoodaOrderPayment payment) {
        payment.setOrder(this);
        return payment;
    }

    public void setPayments(List<FoodaOrderPayment> payments) {
        if(payments == null) this.payments = new ArrayList<>();
        this.payments = payments.stream()
                .map(this::setOnePayment)
                .collect(Collectors.toList());
    }
}
