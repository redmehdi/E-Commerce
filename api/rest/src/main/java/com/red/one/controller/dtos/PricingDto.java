package com.red.one.controller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PricingDto {
    @JsonProperty("BRAND_ID")
    private Long id;
    @JsonProperty("PRICE")
    private String price;
    @JsonProperty("PRODUCT_ID")
    private String product_id;
}