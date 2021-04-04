package be.fooda.backend.order.model.entity;

import be.fooda.backend.commons.model.delivery.FoodaDeliveryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@Entity
public class FoodaOrderDelivery{

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalDeliveryId;

    @Field
    @PositiveOrZero
    private BigDecimal cost;

    @Field
    @Enumerated(EnumType.STRING)
    private FoodaDeliveryStatus status = FoodaDeliveryStatus.ON_DELIVERY_PARENT;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @ContainedIn
    @JsonIgnoreProperties("delivery")
    private FoodaOrder order;
}
