package be.fooda.backend.order.service.mapper;

import be.fooda.backend.commons.model.order.create.FoodaOrderPaymentCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderPaymentExample;
import be.fooda.backend.commons.model.order.update.FoodaOrderPaymentUpdate;
import be.fooda.backend.order.model.entity.FoodaOrderPayment;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FoodaOrderPaymentMapper {
    
    FoodaOrderPayment fromCreateToEntity(FoodaOrderPaymentCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderPayment fromUpdateToEntity(FoodaOrderPaymentUpdate from, @MappingTarget FoodaOrderPayment to);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderPayment fromExampleToEntity(FoodaOrderPaymentExample from);

    FoodaOrderPayment fromEntityToCreate(FoodaOrderPaymentCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderPayment fromEntityToUpdate(FoodaOrderPaymentUpdate from, @MappingTarget FoodaOrderPayment to);

    FoodaOrderPayment fromEntityToExample(FoodaOrderPaymentExample from);

   }
