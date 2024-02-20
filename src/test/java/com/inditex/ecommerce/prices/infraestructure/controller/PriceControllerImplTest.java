package com.inditex.ecommerce.prices.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.ecommerce.prices.Constant;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerImplTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objMapper;

    @Test
    void endpointPrice_whenExists_shouldReturnPriceTest01() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        PriceDTO expectedPriceDto = PriceDTO.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP))
                .build();

        @SuppressWarnings("null")
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        PriceDTO resultPriceDTO = objMapper.readValue(json, PriceDTO.class);

        assertEquals(resultPriceDTO, expectedPriceDto);
    }

    @Test
    void endpointPrice_whenExists_shouldReturnPriceTest02() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

        PriceDTO expectedPriceDto = PriceDTO.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30,0))
                .priceList(2L)
                .price(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP))
                .build();

        @SuppressWarnings("null")
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        PriceDTO resultPriceDTO = objMapper.readValue(json, PriceDTO.class);

        assertEquals(resultPriceDTO, expectedPriceDto);
    }

    @Test
    void endpointPrice_whenExists_shouldReturnPriceTest03() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);

        PriceDTO expectedPriceDto = PriceDTO.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP))
                .build();

        @SuppressWarnings("null")
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        PriceDTO resultPriceDTO = objMapper.readValue(json, PriceDTO.class);

        assertEquals(resultPriceDTO, expectedPriceDto);
    }

    @Test
    void endpointPrice_whenExists_shouldReturnPriceTest04() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

        PriceDTO expectedPriceDto = PriceDTO.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11,0,0))
                .priceList(3L)
                .price(BigDecimal.valueOf(30.50).setScale(2, RoundingMode.HALF_UP))
                .build();

        @SuppressWarnings("null")
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        PriceDTO resultPriceDTO = objMapper.readValue(json, PriceDTO.class);

        assertEquals(resultPriceDTO, expectedPriceDto);
    }

    @Test
    void endpointPrice_whenExists_shouldReturnPriceTest05() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

        PriceDTO expectedPriceDto = PriceDTO.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23,59,59))
                .priceList(4L)
                .price(BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_UP))
                .build();

        @SuppressWarnings("null")
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        PriceDTO resultPriceDTO = objMapper.readValue(json, PriceDTO.class);

        assertEquals(resultPriceDTO, expectedPriceDto);
    }

    @Test
    void endpointPrice_whenGetSeveralResult_shouldGetHighestPriority() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 17, 0, 0);

        PriceDTO expectedPriceDto = PriceDTO.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30,0))
                .priceList(2L)
                .price(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP))
                .build();

        @SuppressWarnings("null")
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        PriceDTO resultPriceDTO = objMapper.readValue(json, PriceDTO.class);

        assertEquals(resultPriceDTO, expectedPriceDto);
    }

    @Test
    void endpointPrice_whenNotExist_shouldReturnNotFound() throws Exception{

        int brandId = 1;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2010, 6, 16, 21, 0, 0);
        
        
        mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andReturn();
    }

    @Test
    void endpointPrice_whenParameterIncorrect_shouldReturnBadRequest() throws Exception{

        int brandId = 1;
        String productId = "354j45";
        LocalDateTime date = LocalDateTime.of(2010, 6, 16, 21, 0, 0);

        mockMvc.perform(MockMvcRequestBuilders
                                .get(String.format(Constant.URL_INCORRECT, date, productId, brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andReturn();
    }
}
