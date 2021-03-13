package com.red.one;

import com.red.one.domain.entities.Pricing;
import com.red.one.entities.PricingEntity;

public final class PokeDataset {

    public static Pricing[] getSpecies() {
        return new Pricing[]{
                new Pricing(1L, "color1", "name1"),
                new Pricing(2L, "color2", "name2"),
                new Pricing(3L, "color3", "name3"),
                new Pricing(4L, "color4", "name4"),
                new Pricing(5L, "color5", "name5")
        };
    }

    public static PricingEntity[] getSpeciesEntity() {
        return new PricingEntity[]{
                new PricingEntity("color1", "name1", "url1", "idExt1"),
                new PricingEntity("color2", "name2", "url2", "idExt2"),
                new PricingEntity("color3", "name3", "url3", "idExt3"),
                new PricingEntity("color4", "name4", "url4", "idExt4"),
                new PricingEntity("color5", "name5", "url5", "idExt5")
        };
    }

    public static PricingEntity getSpecie(final Long id, final String color, final String name, final String url,
                                              final String idExt) {
        return new PricingEntity(color, name, url, idExt);
    }
}
