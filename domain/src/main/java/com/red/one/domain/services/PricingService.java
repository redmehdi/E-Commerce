package com.red.one.domain.services;

import com.red.one.domain.entities.Pricing;
import com.red.one.domain.ports.PricingPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService implements IPricingService {

    private final PricingPort port;

    public PricingService(final PricingPort port) {
        this.port = port;
    }

    @Override
    public List<Pricing> getCurrentPrice() {
        return this.port.findPrice();
    }
}