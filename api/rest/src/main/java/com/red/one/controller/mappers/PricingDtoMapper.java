package com.red.one.controller.mappers;

import com.red.one.controller.dtos.PricingDto;
import com.red.one.domain.entities.Pricing;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PricingDtoMapper {

    PricingDto map(final Pricing domain);
}