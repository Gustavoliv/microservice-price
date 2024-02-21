package com.inditex.ecommerce.prices.infraestructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.port.in.PriceDomainRepository;
import com.inditex.ecommerce.prices.infraestructure.entity.PriceEntity;
import com.inditex.ecommerce.prices.infraestructure.rest.mapper.PriceMapper;

@Repository
public class PriceRepositoryH2 implements PriceDomainRepository{

    private final PriceRepository priceRepository;

    private PriceMapper priceMapper;

    public PriceRepositoryH2(PriceRepository priceRepository, PriceMapper priceMapper){
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public Optional<Price> getPriceByBrandProductDate(Price priceSearch) {

        Optional<PriceEntity> priceEntity = priceRepository
            .findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(priceSearch.getStartDate(),
                priceSearch.getEndDate(), priceSearch.getBrand().getBrandId(), priceSearch.getProduct().getProductId());

        return priceEntity.map(this.priceMapper::toPrice);
    }
}
