package com.red.one.repositories;

import com.red.one.PokeDataset;
import com.red.one.domain.entities.Pricing;
import com.red.one.entities.PricingEntity;
import com.red.one.mappers.PricingMapper;
import com.red.one.mappers.PricingMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokeAdapterTest {
    @Mock
    private PricingRepository repository;

    @Spy
    private PricingMapper mapper = new PricingMapperImpl();

    @InjectMocks
    private PricingAdapter port;

    @Before
    public void onBefore() throws Exception {
        MockitoAnnotations.initMocks(this);
        setUp();
    }

    public void setUp() throws Exception {
        // Method implemented when required
    }

    @Test
    public void findTop5ByHeight_whenNotNull_shouldExpectedSize() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        int expectedSize = expectedEntities.length;
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = port.findPrice();

        Assertions.assertNotNull(result);
        Assert.assertTrue(result.size() == expectedSize);
    }

    @Test
    public void findTop5ByHeight_whenNotNull_shouldExpectedValues() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = port.findPrice();

        Assertions.assertNotNull(result);
        Pricing pricing = result.get(0);
        Assert.assertNull(pricing.getId());
        Assert.assertEquals(expectedEntities[0].getColor(), pricing.getColor());
        Assert.assertEquals(expectedEntities[0].getName(), pricing.getName());
    }

    @Test
    public void findTop5ByHeight_whenNull_shouldExpectedEmptyList() {
        mockFindTop5ByHeight(Collections.EMPTY_LIST);

        final List<Pricing> result = port.findPrice();

        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void findTop5ByHeight_whenNotNull_shouldCallOnceRepository() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = port.findPrice();

        Assertions.assertNotNull(result);
        Mockito.verify(repository, Mockito.times(1)).findPrice();
        Mockito.verify(repository, Mockito.never()).findTop5ByWeight();
        Mockito.verify(repository, Mockito.never()).findTop5ByBaseExperience();
    }

    @Test(expected = RuntimeException.class)
    public void findTop5ByHeight_whenNotNull_shouldExpectedException() {
        mockFindTop5ByHeight(new RuntimeException());

        port.findPrice();
    }

    @Test
    public void findTop5ByHeight_whenNotNull_shouldCall5TimesMapper() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        final int expectedSize = expectedEntities.length;
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = port.findPrice();

        Assertions.assertNotNull(result);
        Mockito.verify(mapper, Mockito.times(expectedSize)).map(Mockito.any(PricingEntity.class));
        Mockito.verify(repository, Mockito.never()).findTop5ByWeight();
        Mockito.verify(repository, Mockito.never()).findTop5ByBaseExperience();
    }

    @Test
    public void findTop5ByWeight_whenNotNull_shouldExpectedSize() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        int expectedSize = expectedEntities.length;
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByWeight(values);

        final List<Pricing> result = port.findTop5ByWeight();

        Assertions.assertNotNull(result);
        Assert.assertTrue(result.size() == expectedSize);
    }

    @Test
    public void findTop5ByWeight_whenNotNull_shouldExpectedValues() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByWeight(values);

        final List<Pricing> result = port.findTop5ByWeight();

        Assertions.assertNotNull(result);
        Pricing pricing = result.get(0);
        Assert.assertNull(pricing.getId());
        Assert.assertEquals(expectedEntities[0].getColor(), pricing.getColor());
        Assert.assertEquals(expectedEntities[0].getName(), pricing.getName());
    }

    @Test
    public void findTop5ByWeight_whenNull_shouldExpectedEmptyList() {
        mockFindTop5ByWeight(Collections.EMPTY_LIST);

        final List<Pricing> result = port.findTop5ByWeight();

        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void findTop5ByWeight_whenNotNull_shouldCallOnceRepository() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByWeight(values);

        final List<Pricing> result = port.findTop5ByWeight();

        Assertions.assertNotNull(result);
        Mockito.verify(repository, Mockito.times(1)).findTop5ByWeight();
        Mockito.verify(repository, Mockito.never()).findPrice();
        Mockito.verify(repository, Mockito.never()).findTop5ByBaseExperience();
    }

    @Test(expected = RuntimeException.class)
    public void findTop5ByWeight_whenNotNull_shouldExpectedException() {
        mockFindTop5ByWeight(new RuntimeException());

        port.findTop5ByWeight();
    }

    @Test
    public void findTop5ByWeight_whenNotNull_shouldCall5TimesMapper() {
        final PricingEntity[] expectedEntities = PokeDataset.getSpeciesEntity();
        final int expectedSize = expectedEntities.length;
        List<PricingEntity> values = Arrays.asList(expectedEntities);
        mockFindTop5ByWeight(values);

        final List<Pricing> result = port.findTop5ByWeight();

        Assertions.assertNotNull(result);
        Mockito.verify(mapper, Mockito.times(expectedSize)).map(Mockito.any(PricingEntity.class));
        Mockito.verify(repository, Mockito.never()).findPrice();
        Mockito.verify(repository, Mockito.never()).findTop5ByBaseExperience();
    }

    private void mockFindTop5ByHeight(final List<PricingEntity> values) {
        Mockito.when(repository.findPrice()).thenReturn(values);
    }

    private void mockFindTop5ByHeight(final RuntimeException values) {
        Mockito.when(repository.findPrice()).thenThrow(values);
    }

    private void mockFindTop5ByWeight(final List<PricingEntity> values) {
        Mockito.when(repository.findTop5ByWeight()).thenReturn(values);
    }

    private void mockFindTop5ByWeight(final RuntimeException values) {
        Mockito.when(repository.findTop5ByWeight()).thenThrow(values);
    }
}