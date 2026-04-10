package com.xyz.booking.service;

import com.xyz.booking.dto.BookingRequest;
import com.xyz.booking.dto.BookingResponse;
import com.xyz.booking.entity.*;
import com.xyz.booking.enums.SeatStatus;
import com.xyz.booking.repository.*;
import com.xyz.booking.exception.SeatNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired private SeatInventoryRepository seatRepo;
    @Autowired private ShowRepository showRepo;
    @Autowired private BookingRepository bookingRepo;
    @Autowired private DiscountStrategy discountStrategy;

    @Transactional
    public BookingResponse bookTickets(BookingRequest request) {
        // 1. Lock seats pessimistically
        List<SeatInventory> seats = seatRepo.findAllByShowIdAndSeatNumberInWithLock(
                request.getShowId(), request.getSeats());

        // Verify all seats are AVAILABLE
        for (SeatInventory seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new SeatNotAvailableException("Seat " + seat.getId().getSeatNumber() + " is not available");
            }
            seat.setStatus(SeatStatus.BLOCKED);
            seatRepo.save(seat);
        }

        // 2. Get show details
        Show show = showRepo.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        // 3. Calculate discounts
        BigDecimal baseTotal = show.getPricePerSeat().multiply(BigDecimal.valueOf(request.getSeats().size()));
        BigDecimal discountedTotal = discountStrategy.applyAll(baseTotal, show, request.getSeats().size());
        BigDecimal discountAmount = baseTotal.subtract(discountedTotal);

        // 4. Create booking record
        Booking booking = new Booking();
        booking.setShowId(show.getId());
        booking.setCustomerEmail(request.getCustomerEmail());
        booking.setTotalAmount(discountedTotal);
        booking.setDiscountAmount(discountAmount);
        booking.setStatus("PENDING");
        booking = bookingRepo.save(booking);

        // 5. Associate seats with booking (keep BLOCKED)
        for (SeatInventory seat : seats) {
            seat.setBookingId(booking.getId());
            seatRepo.save(seat);
        }

        // 6. Mock payment URL (in real scenario call payment gateway)
        String paymentUrl = "https://pay.xyz.com/pay?bookingId=" + booking.getId();

        // 7. Build discount breakdown for response
        Map<String, String> discountBreakup = new LinkedHashMap<>();
        if (show.getShowTime().getHour() >= 12 && show.getShowTime().getHour() < 16) {
            discountBreakup.put("afternoonShowDiscount", "20% on all seats");
        }
        if (request.getSeats().size() >= 3) {
            discountBreakup.put("thirdTicketDiscount", "50% on third seat");
        }
        if (discountBreakup.isEmpty()) {
            discountBreakup.put("noDiscount", "No discounts applied");
        }

        return new BookingResponse(
                "BKN" + booking.getId(),
                discountedTotal,
                discountBreakup,
                request.getSeats(),
                "PENDING_PAYMENT",
                paymentUrl
        );
    }
}
