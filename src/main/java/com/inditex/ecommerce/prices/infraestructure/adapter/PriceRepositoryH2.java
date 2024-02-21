package com.inditex.ecommerce.prices.infraestructure.adapter;

import org.springframework.stereotype.Repository;

import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.port.in.PriceDomainRepository;
import com.inditex.ecommerce.prices.infraestructure.exception.NotFoundException;
import com.inditex.ecommerce.prices.infraestructure.rest.constants.Constant;
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
    public Price getPriceByBrandProductDate(Price priceSearch) {

        return priceRepository
            .findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                priceSearch.getStartDate(),priceSearch.getEndDate(), priceSearch.getBrand().getBrandId(),
                priceSearch.getProduct().getProductId()
            )
            .map(this.priceMapper::toPrice)
            .orElseThrow(() -> new NotFoundException(Constant.PRICE_NOT_FOUND));
    }
}
