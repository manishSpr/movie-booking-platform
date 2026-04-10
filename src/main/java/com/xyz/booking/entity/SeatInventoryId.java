package com.xyz.booking.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatInventoryId implements Serializable {
    private Long showId;
    private String seatNumber;

    public SeatInventoryId() {}
    public SeatInventoryId(Long showId, String seatNumber) {
        this.showId = showId;
        this.seatNumber = seatNumber;
    }

    // Getters, Setters, equals, hashCode
    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatInventoryId that = (SeatInventoryId) o;
        return Objects.equals(showId, that.showId) && Objects.equals(seatNumber, that.seatNumber);
    }
    @Override
    public int hashCode() { return Objects.hash(showId, seatNumber); }
}
