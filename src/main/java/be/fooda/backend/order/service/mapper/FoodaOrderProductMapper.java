package be.fooda.backend.order.service.mapper;

import be.fooda.backend.commons.model.order.create.FoodaOrderProductCreate;
import be.fooda.backend.commons.model.order.read.FoodaOrderProductExample;
import be.fooda.backend.commons.model.order.update.FoodaOrderProductUpdate;
import be.fooda.backend.order.model.entity.FoodaOrderProduct;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FoodaOrderProductMapper {

    FoodaOrderProduct fromCreateToEntity(FoodaOrderProductCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderProduct fromUpdateToEntity(FoodaOrderProductUpdate from, @MappingTarget FoodaOrderProduct to);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderProduct fromExampleToEntity(FoodaOrderProductExample from);

    FoodaOrderProduct fromEntityToCreate(FoodaOrderProductCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaOrderProduct fromEntityToUpdate(FoodaOrderProductUpdate from, @MappingTarget FoodaOrderProduct to);

    FoodaOrderProduct fromEntityToExample(FoodaOrderProductExample from);
}
