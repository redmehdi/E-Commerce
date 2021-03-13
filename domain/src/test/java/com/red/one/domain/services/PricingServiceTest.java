package com.red.one.domain.services;

import com.red.one.domain.entities.Pricing;
import com.red.one.domain.ports.PricingPort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PricingServiceTest {

    @Mock
    private PricingPort port;

    @InjectMocks
    private PricingService service;

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
        final Pricing[] expectedDomain = PokeDataset.getSpecies();
        int expectedSize = expectedDomain.length;
        List<Pricing> values = Arrays.asList(expectedDomain);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = service.getCurrentPrice();

        Assertions.assertNotNull(result);
        Assert.assertTrue(result.size() == expectedSize);
    }

    private void mockFindTop5ByHeight(final List<Pricing> values) {
        Mockito.when(port.findPrice()).thenReturn(values);
    }

    private void mockFindTop5ByHeight(final RuntimeException values) {
        Mockito.when(port.findPrice()).thenThrow(values);
    }

    @Test
    public void findTop5ByHeight_whenNotNull_shouldExpectedValues() {
        final Pricing[] expectedDomain = PokeDataset.getSpecies();
        List<Pricing> values = Arrays.asList(expectedDomain);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = service.getCurrentPrice();

        Assertions.assertNotNull(result);
        Pricing pricing = result.get(0);
        Assert.assertEquals(expectedDomain[0].getId(), pricing.getId());
        Assert.assertEquals(expectedDomain[0].getColor(), pricing.getColor());
        Assert.assertEquals(expectedDomain[0].getName(), pricing.getName());
    }

    @Test
    public void findTop5ByHeight_whenNull_shouldExpectedEmptyList() {
        mockFindTop5ByHeight(Collections.EMPTY_LIST);

        final List<Pricing> result = service.getCurrentPrice();

        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void findTop5ByHeight_whenNotNull_shouldCallOnceRepository() {
        final Pricing[] expectedDomain = PokeDataset.getSpecies();
        List<Pricing> values = Arrays.asList(expectedDomain);
        mockFindTop5ByHeight(values);

        final List<Pricing> result = service.getCurrentPrice();

        Assertions.assertNotNull(result);
        Mockito.verify(port, Mockito.times(1)).findPrice();
        Mockito.verify(port, Mockito.never()).findTop5ByWeight();
        Mockito.verify(port, Mockito.never()).findTop5ByBaseExperience();
    }

    @Test(expected = RuntimeException.class)
    public void findTop5ByHeight_whenNotNull_shouldExpectedException() {
        mockFindTop5ByHeight(new RuntimeException());

        service.getCurrentPrice();
    }

    //The same unit test for findTop5ByWeight and findTop5ByBaseExperience
}