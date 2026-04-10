package com.xyz.booking.service;

import com.xyz.booking.discount.Discount;
import com.xyz.booking.entity.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountStrategy {
    private final List<Discount> discounts;

    @Autowired
    public DiscountStrategy(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public BigDecimal applyAll(BigDecimal amount, Show show, int seatCount) {
        BigDecimal result = amount;
        for (Discount discount : discounts) {
            result = discount.apply(result, show, seatCount);
        }
        return result;
    }
}
