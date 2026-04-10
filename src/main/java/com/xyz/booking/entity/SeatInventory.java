package com.xyz.booking.entity;

import com.xyz.booking.enums.SeatStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "seat_inventory")
public class SeatInventory {
    @EmbeddedId
    private SeatInventoryId id;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long bookingId; // nullable, links to Booking if BOOKED or BLOCKED

    public SeatInventory() {}
    public SeatInventory(SeatInventoryId id, SeatStatus status) {
        this.id = id;
        this.status = status;
    }

    // Getters and Setters
    public SeatInventoryId getId() { return id; }
    public void setId(SeatInventoryId id) { this.id = id; }
    public SeatStatus getStatus() { return status; }
    public void setStatus(SeatStatus status) { this.status = status; }
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
}