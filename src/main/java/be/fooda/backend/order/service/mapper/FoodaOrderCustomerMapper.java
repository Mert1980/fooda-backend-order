package be.fooda.backend.order.service.mapper;

import be.fooda.backend.commons.model.order.create.FoodaOrderCustomerCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderCustomerExample;
import be.fooda.backend.commons.model.order.update.FoodaOrderCustomerUpdate;
import be.fooda.backend.order.model.entity.FoodaOrderCustomer;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FoodaOrderCustomerMapper {

    FoodaOrderCustomer fromCreateToEntity(FoodaOrderCustomerCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderCustomer fromUpdateToEntity(FoodaOrderCustomerUpdate from, @MappingTarget FoodaOrderCustomer to);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderCustomer fromExampleToEntity(FoodaOrderCustomerExample from);

    FoodaOrderCustomer fromEntityToCreate(FoodaOrderCustomerCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderCustomer fromEntityToUpdate(FoodaOrderCustomerUpdate from, @MappingTarget FoodaOrderCustomer to);

    FoodaOrderCustomer fromEntityToExample(FoodaOrderCustomerExample from);
}
