package com.red.one.domain.ports;

import com.red.one.domain.entities.Pricing;
import java.util.List;
import java.util.Optional;

public interface PricingPort {

    Optional<Pricing> findById(final Long id);

    List<Pricing> findPrice();
}
