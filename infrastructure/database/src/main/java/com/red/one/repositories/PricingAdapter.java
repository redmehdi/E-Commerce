package com.red.one.repositories;

import com.red.one.domain.entities.Pricing;
import com.red.one.domain.ports.PricingPort;
import com.red.one.mappers.PricingMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("pricingAdapter")
public class PricingAdapter implements PricingPort {

    private final PricingRepository repository;
    private final PricingMapper mapper;

    public PricingAdapter(final PricingRepository repository, final PricingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Pricing> findById(final Long id) {
        return repository.findById(id).map(mapper::map);
    }

    @Override
    public List<Pricing> findPrice() {
        return repository.findPrice()
                .stream().filter(Objects::nonNull)
                .map(this.mapper::map).collect(Collectors.toList());
    }

}