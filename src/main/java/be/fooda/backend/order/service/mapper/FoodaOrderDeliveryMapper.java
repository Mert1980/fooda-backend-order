package be.fooda.backend.order.service.mapper;

import be.fooda.backend.commons.model.order.create.FoodaOrderDeliveryCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderDeliveryExample;
import be.fooda.backend.commons.model.order.update.FoodaOrderDeliveryUpdate;
import be.fooda.backend.order.model.entity.FoodaOrderDelivery;
import org.mapstruct.*;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FoodaOrderDeliveryMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderDelivery fromCreateToEntity(FoodaOrderDeliveryCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderDelivery fromUpdateToEntity(FoodaOrderDeliveryUpdate from, @MappingTarget FoodaOrderDelivery to);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderDelivery fromExampleToEntity(FoodaOrderDeliveryExample from);

    FoodaOrderDelivery fromEntityToCreate(FoodaOrderDeliveryCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderDelivery fromEntityToUpdate(FoodaOrderDeliveryUpdate from, @MappingTarget FoodaOrderDelivery to);

    FoodaOrderDelivery fromEntityToExample(FoodaOrderDeliveryExample from);
}
