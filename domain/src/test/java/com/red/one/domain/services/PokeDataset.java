package com.red.one.domain.services;

import com.red.one.domain.entities.Pricing;

public final class PokeDataset {

    public static Pricing[] getSpecies() {
        return new Pricing[]{
                new Pricing(1L, "color1", "name1", getCharacters()[0]),
                new Pricing(2L, "color2", "name2", getCharacters()[1]),
                new Pricing(3L, "color3", "name3", getCharacters()[2]),
                new Pricing(4L, "color4", "name4", getCharacters()[3]),
                new Pricing(5L, "color5", "name5", getCharacters()[4])
        };
    }

    public static PokeCharacter[] getCharacters() {
        return new PokeCharacter[]{
                new PokeCharacter(1L, "name1", 3, 3, 3),
                new PokeCharacter(2L, "name2", 3, 3, 34),
                new PokeCharacter(3L, "name3", 3, 3, 5),
                new PokeCharacter(4L, "name4", 3, 3, 23),
                new PokeCharacter(5L, "name5", 3, 3, 88)
        };
    }

}
