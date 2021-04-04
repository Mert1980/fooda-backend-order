package be.fooda.backend.order.service.mapper;

import be.fooda.backend.commons.model.order.create.FoodaOrderStoreCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderStoreExample;
import be.fooda.backend.commons.model.order.update.FoodaOrderStoreUpdate;
import be.fooda.backend.order.model.entity.FoodaOrderStore;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FoodaOrderStoreMapper {

    FoodaOrderStore fromCreateToEntity(FoodaOrderStoreCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderStore fromUpdateToEntity(FoodaOrderStoreUpdate from, @MappingTarget FoodaOrderStore to);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderStore fromExampleToEntity(FoodaOrderStoreExample from);

    FoodaOrderStore fromEntityToCreate(FoodaOrderStoreCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderStore fromEntityToUpdate(FoodaOrderStoreUpdate from, @MappingTarget FoodaOrderStore to);

    FoodaOrderStore fromEntityToExample(FoodaOrderStoreExample from);
}
