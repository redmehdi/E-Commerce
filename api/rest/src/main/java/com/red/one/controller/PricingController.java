package com.red.one.controller;

import com.red.one.controller.dtos.PricingDto;
import com.red.one.controller.mappers.PricingDtoMapper;
import com.red.one.domain.services.IPricingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    private final IPricingService service;
    private final PricingDtoMapper mapper;

    public PricingController(final IPricingService service, final PricingDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Retrieves pricing compain
     *
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    List<PricingDto> getTop5ByHeight() {
        return service.getCurrentPrice().stream().map(this.mapper::map).collect(Collectors.toList());
    }
}