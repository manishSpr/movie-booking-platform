package com.xyz.booking.discount;

import com.xyz.booking.entity.Show;
import java.math.BigDecimal;

public interface Discount {
    BigDecimal apply(BigDecimal amount, Show show, int numberOfSeats);
}
