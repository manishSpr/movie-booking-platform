package com.xyz.booking.discount;

import com.xyz.booking.entity.Show;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class AfternoonShowDiscount implements Discount {
    @Override
    public BigDecimal apply(BigDecimal amount, Show show, int numberOfSeats) {
        int hour = show.getShowTime().getHour();
        if (hour >= 12 && hour < 16) {
            return amount.multiply(BigDecimal.valueOf(0.8)); // 20% off
        }
        return amount;
    }
}
