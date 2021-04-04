package be.fooda.backend.order.service.mapper;

import be.fooda.backend.commons.model.order.create.FoodaOrderCreate;
import be.fooda.backend.commons.model.order.create.FoodaOrderProductCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderExample;
import be.fooda.backend.commons.model.order.update.FoodaOrderProductUpdate;
import be.fooda.backend.commons.model.order.update.FoodaOrderUpdate;
import be.fooda.backend.order.model.entity.FoodaOrder;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FoodaOrderMapper {


    @Mapping(target = "productsTotal", expression = "java(calculateProductsTotal(from))")
    @Mapping(target = "deliveryTotal", expression = "java(calculateDeliveryTotal(from))")
    @Mapping(target = "taxTotal", expression = "java(calculateTaxTotal(from))")
    @Mapping(target = "priceTotal", expression = "java(calculatePriceTotal(from))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrder fromCreateToEntity(FoodaOrderCreate from);

    @Mapping(target = "productsTotal", expression = "java(calculateProductsTotal(from))")
    @Mapping(target = "deliveryTotal", expression = "java(calculateDeliveryTotal(from))")
    @Mapping(target = "taxTotal", expression = "java(calculateTaxTotal(from))")
    @Mapping(target = "priceTotal", expression = "java(calculatePriceTotal(from))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrder fromUpdateToEntity(FoodaOrderUpdate from, @MappingTarget FoodaOrder to);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrder fromExampleToEntity(FoodaOrderExample from);

    FoodaOrderCreate fromEntityToCreate(FoodaOrder from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderUpdate fromEntityToUpdate(FoodaOrder from, @MappingTarget FoodaOrderUpdate to);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderExample fromEntityToExample(FoodaOrder from);

    default BigDecimal calculateProductsTotal(FoodaOrderCreate toCreate) {

        final List<FoodaOrderProductCreate> products = toCreate.getProducts();
        double total = 0.00;

        for (FoodaOrderProductCreate product : products) {
            // TAX IS INCLUSIVE ..
            total = total + product.getPrice().doubleValue();
        }
        return BigDecimal.valueOf(total);
    }
    default BigDecimal calculateProductsTotal(FoodaOrderUpdate toUpdate) {

        final List<FoodaOrderProductUpdate> products = toUpdate.getProducts();
        double total = 0.00;

        for (FoodaOrderProductUpdate product : products) {
            // TAX IS INCLUSIVE ..
            total = total + product.getPrice().doubleValue();
        }
        return BigDecimal.valueOf(total);
    }

    default BigDecimal calculateDeliveryTotal(FoodaOrderCreate toCreate) {
        return toCreate.getDelivery().getCost();
    }

    default BigDecimal calculateDeliveryTotal(FoodaOrderUpdate toUpdate) {
        return toUpdate.getDelivery().getCost();
    }

    default BigDecimal calculateTaxTotal(FoodaOrderCreate toCreate) {
        final List<FoodaOrderProductCreate> products = toCreate.getProducts();
        double total = 0.00;
        for (FoodaOrderProductCreate product : products) {
            total = total + (product.getTax().multiply(product.getPrice()).doubleValue());
        }
        return BigDecimal.valueOf(total);
    }

    default BigDecimal calculateTaxTotal(FoodaOrderUpdate toUpdate) {
        final List<FoodaOrderProductUpdate> products = toUpdate.getProducts();
        double total = 0.00;
        for (FoodaOrderProductUpdate product : products) {
            total = total + (product.getTax().multiply(product.getPrice()).doubleValue());
        }
        return BigDecimal.valueOf(total);
    }

    default BigDecimal calculatePriceTotal(FoodaOrderCreate toCreate) {

        double discount = 1.00;

        if (toCreate.getDiscount() != null && toCreate.getDiscount().doubleValue() > 0.00) {
            discount = toCreate.getDiscount().doubleValue();
        }

        final BigDecimal productsTotal = calculateProductsTotal(toCreate);
        final BigDecimal deliveryTotal = calculateDeliveryTotal(toCreate);
        return productsTotal.add(deliveryTotal).multiply(BigDecimal.valueOf(discount));
    }
    default BigDecimal calculatePriceTotal(FoodaOrderUpdate toUpdate) {

        double discount = 1.00;

        if (toUpdate.getDiscount() != null && toUpdate.getDiscount().doubleValue() > 0.00) {
            discount = toUpdate.getDiscount().doubleValue();
        }

        final BigDecimal productsTotal = calculateProductsTotal(toUpdate);
        final BigDecimal deliveryTotal = calculateDeliveryTotal(toUpdate);
        return productsTotal.add(deliveryTotal).multiply(BigDecimal.valueOf(discount));
    }

}