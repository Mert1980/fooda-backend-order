package be.fooda.backend.order.model.entity;

import be.fooda.backend.order.service.validation.Name;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@Entity
public class FoodaOrderProduct {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    private Long externalProductId;

    @Min(value = 1)
    @Field
    @SortableField
    private Integer quantity;

    @Field
    @Name
    private String productName;

    @Column(columnDefinition = "DECIMAL(8,2)")
    @Field
    @Min(value = 0)
    @SortableField
    private BigDecimal price;

    @Column(columnDefinition = "DECIMAL(8,2)")
    @Field
    @Min(value = 0)
    private BigDecimal tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @ContainedIn
    @JsonIgnoreProperties("products")
    private FoodaOrder order;
}