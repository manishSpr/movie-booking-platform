package com.xyz.booking.dto;

import java.math.BigDecimal;
import java.util.Map;

public class BookingResponse {
    private String bookingId;
    private BigDecimal totalAmount;
    private Map<String, String> discountBreakup;
    private java.util.List<String> seatsBooked;
    private String status;
    private String paymentUrl;

    public BookingResponse(String bookingId, BigDecimal totalAmount, Map<String, String> discountBreakup,
                           java.util.List<String> seatsBooked, String status, String paymentUrl) {
        this.bookingId = bookingId;
        this.totalAmount = totalAmount;
        this.discountBreakup = discountBreakup;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.paymentUrl = paymentUrl;
    }
    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public Map<String, String> getDiscountBreakup() { return discountBreakup; }
    public void setDiscountBreakup(Map<String, String> discountBreakup) { this.discountBreakup = discountBreakup; }
    public java.util.List<String> getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(java.util.List<String> seatsBooked) { this.seatsBooked = seatsBooked; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentUrl() { return paymentUrl; }
    public void setPaymentUrl(String paymentUrl) { this.paymentUrl = paymentUrl; }
}
