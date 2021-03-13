package com.red.one.domain.services;

import com.red.one.domain.entities.Pricing;
import java.util.List;
import java.util.Optional;

public interface IPricingService {

    List<Pricing> getCurrentPrice();

}
