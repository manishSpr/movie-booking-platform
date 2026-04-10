package com.xyz.booking.discount;

import com.xyz.booking.entity.Show;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ThirdTicketDiscount implements Discount {
    @Override
    public BigDecimal apply(BigDecimal amount, Show show, int numberOfSeats) {
        if (numberOfSeats >= 3) {
            BigDecimal perSeat = amount.divide(BigDecimal.valueOf(numberOfSeats), RoundingMode.HALF_UP);
            BigDecimal discountOnThird = perSeat.multiply(BigDecimal.valueOf(0.5));
            return amount.subtract(discountOnThird);
        }
        return amount;
    }
}
