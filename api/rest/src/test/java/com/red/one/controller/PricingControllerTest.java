package com.red.one.controller;

import com.red.one.controller.dtos.PricingDto;
import com.red.one.controller.mappers.PricingDtoMapper;
import com.red.one.controller.mappers.PricingDtoMapperImpl;
import com.red.one.domain.entities.Pricing;
import com.red.one.domain.services.PricingService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PricingControllerTest extends BaseTest {

    @Mock
    private PricingService service;

    @Spy
    private PricingDtoMapper mapper = new PricingDtoMapperImpl();

    @InjectMocks
    private PricingController controller;

    @Test
    public void testGetTop5ByHeight_whenExistsData_shouldCallService() throws Exception {
        final Pricing[] expected = PokeDtoDataset.getSpecies();
        mockGetTop5ByHeight(Arrays.asList(expected));

        controller.getTop5ByHeight();

        Mockito.verify(this.service, Mockito.times(1))
                .getCurrentPrice();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testGetTop5ByHeight_whenExistsData_shouldCallMapper() throws Exception {
        final Pricing[] expected = PokeDtoDataset.getSpecies();
        mockGetTop5ByHeight(Arrays.asList(expected));

        controller.getTop5ByHeight();

        Mockito.verify(this.mapper, Mockito.times(5))
                .map(Mockito.any(Pricing.class));
    }

    @Test
    public void testGetTop5ByHeight_whenExistsData_shouldExpectedValues() throws Exception {
        final Pricing[] expected = PokeDtoDataset.getSpecies();
        final List<Pricing> expectedList = Arrays.asList(expected);
        mockGetTop5ByHeight(expectedList);

        final List<PricingDto> result = controller.getTop5ByHeight();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedList.size(), result.size());
    }

    @Test
    public void testGetTop5ByHeight_whenEmpty_shouldCallExpectedValues() throws Exception {
        mockGetTop5ByHeight(Collections.EMPTY_LIST);

        final List<PricingDto> result = controller.getTop5ByHeight();

        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    private void mockGetTop5ByHeight(final List<Pricing> expected) {
        Mockito.when(service.getCurrentPrice()).thenReturn(expected);
    }

    //The same for other operations
}