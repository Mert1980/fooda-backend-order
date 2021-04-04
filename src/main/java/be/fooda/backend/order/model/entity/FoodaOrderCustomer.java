package be.fooda.backend.order.model.entity;

import be.fooda.backend.order.service.validation.Name;
import be.fooda.backend.order.service.validation.PhoneNumber;
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

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@Entity
public class FoodaOrderCustomer {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalCustomerId;

    @Field
    @SortableField
    @Name
    private String firstName;

    @Field
    @Name
    @SortableField
    private String familyName;

    @Field
    @PhoneNumber
    @SortableField
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @ContainedIn
    @JsonIgnoreProperties("customer")
    private FoodaOrder order;
}