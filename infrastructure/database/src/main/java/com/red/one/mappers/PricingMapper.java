package com.red.one.mappers;

import com.red.one.domain.entities.Pricing;
import com.red.one.entities.PricingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PricingMapper {

    Pricing map(final PricingEntity entity);

    PokeCharacter map(final PokeCharacterEntity entity);
}