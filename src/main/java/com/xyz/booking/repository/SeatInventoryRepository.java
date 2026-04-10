package com.xyz.booking.repository;

import com.xyz.booking.entity.SeatInventory;
import com.xyz.booking.entity.SeatInventoryId;
import com.xyz.booking.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory, SeatInventoryId> {

    long countByIdShowIdAndStatus(Long showId, SeatStatus status);

    // Pessimistic locking query (already correct)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SeatInventory s WHERE s.id.showId = :showId AND s.id.seatNumber IN :seatNumbers")
    List<SeatInventory> findAllByShowIdAndSeatNumberInWithLock(@Param("showId") Long showId,
                                                               @Param("seatNumbers") List<String> seatNumbers);
}