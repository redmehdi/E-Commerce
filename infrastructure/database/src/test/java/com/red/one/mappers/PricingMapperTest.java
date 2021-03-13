package com.red.one.mappers;

import com.red.one.domain.entities.Pricing;
import com.red.one.entities.PricingEntity;
import static org.junit.Assert.*;

import org.junit.Test;

public class PricingMapperTest {

    public static final String COLOR = "color";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String idExt ="idExt";

    private PricingMapper mapper = new PricingMapperImpl();

    @Test
    public void mapToDomainSpecies_whenNull_shouldReturnNull() {
        final Pricing result = mapper.map((PricingEntity) null);

        assertNull(result);
    }

    @Test
    public void mapToDomainSpecies_whenNullParams_shouldExpectedValues() {
        final PricingEntity expected = new PricingEntity(null, null, null,null);

        final Pricing result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getColor());
    }

    @Test
    public void mapToDomainSpecies_whenNotNullParams_shouldExpectedValues() {
        final PricingEntity expected = new PricingEntity(COLOR, NAME, URL, idExt);

        final Pricing result = mapper.map(expected);

        assertNotNull(result);
        assertEquals(expected.getColor(),result.getColor());
        assertEquals(expected.getName(),result.getName());
        assertNull(result.getId());
    }

    @Test
    public void mapToDomainSpecies_whenNullColor_shouldExpectedValues() {
        final PricingEntity expected = new PricingEntity(null, NAME, URL, idExt);

        final Pricing result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getColor());
        assertEquals(expected.getName(),result.getName());
        assertNull(result.getId());
    }

    @Test
    public void mapToDomainSpecies_whenNullName_shouldExpectedValues() {
        final PricingEntity expected = new PricingEntity(COLOR, null, URL, idExt);

        final Pricing result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getName());
        assertEquals(expected.getColor(),result.getColor());
        assertNull(result.getId());
    }

    @Test
    public void mapToDomainSpecies_whenNullPokeCharacter_shouldExpectedValues() {
        final PricingEntity expected = new PricingEntity(COLOR, NAME, URL, idExt);

        final Pricing result = mapper.map(expected);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertNotNull(result.getColor());
        assertNull(result.getId());
    }

    /***
    @Test
    public void mapToDomainCharacter_whenNullParams_shouldExpectedValues() {

        final PokeCharacter result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getHeight());
        assertNull(result.getWeight());
        assertNull(result.getBaseExperience());
    }

    @Test
    public void mapToDomainCharacter_whenNotNullParams_shouldExpectedValues() {
        final PricingEntity speciesEntity = new PricingEntity(COLOR, NAME, URL, idExt, null);
        final PokeCharacterEntity expected = new PokeCharacterEntity(NAME, 3, 4,5,speciesEntity);

        final PokeCharacter result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNotNull(result.getName());
        assertNotNull(result.getHeight());
        assertNotNull(result.getWeight());
        assertNotNull(result.getBaseExperience());
    }

    @Test
    public void mapToDomainCharacter_whenNullName_shouldExpectedValues() {
        final PricingEntity speciesEntity = new PricingEntity(COLOR, NAME, URL, idExt, null);
        final PokeCharacterEntity expected = new PokeCharacterEntity(null, 3, 4,5,speciesEntity);

        final PokeCharacter result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNotNull(result.getHeight());
        assertNotNull(result.getWeight());
        assertNotNull(result.getBaseExperience());
    }

    @Test
    public void mapToDomainCharacter_whenNullBaseExperience_shouldExpectedValues() {
        final PricingEntity speciesEntity = new PricingEntity(COLOR, NAME, URL, idExt, null);
        final PokeCharacterEntity expected = new PokeCharacterEntity(NAME, null, 4,5,speciesEntity);

        final PokeCharacter result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNotNull(result.getName());
        assertNotNull(result.getHeight());
        assertNotNull(result.getWeight());
        assertNull(result.getBaseExperience());
    }

    @Test
    public void mapToDomainCharacter_whenNullHeight_shouldExpectedValues() {
        final PricingEntity speciesEntity = new PricingEntity(COLOR, NAME, URL, idExt, null);
        final PokeCharacterEntity expected = new PokeCharacterEntity(NAME, 43, null,5,speciesEntity);

        final PokeCharacter result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNotNull(result.getName());
        assertNull(result.getHeight());
        assertNotNull(result.getWeight());
        assertNotNull(result.getBaseExperience());
    }

    @Test
    public void mapToDomainCharacter_whenNullWeight_shouldExpectedValues() {
        final PricingEntity speciesEntity = new PricingEntity(COLOR, NAME, URL, idExt, null);
        final PokeCharacterEntity expected = new PokeCharacterEntity(NAME, 43, 324,null,speciesEntity);

        final PokeCharacter result = mapper.map(expected);

        assertNotNull(result);
        assertNull(result.getId());
        assertNotNull(result.getName());
        assertNotNull(result.getHeight());
        assertNull(result.getWeight());
        assertNotNull(result.getBaseExperience());
    }*/
}