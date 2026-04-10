package com.xyz.booking.repository;

import com.xyz.booking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    @Query("SELECT s FROM Show s WHERE s.movie.id = :movieId AND s.theatre.city = :city AND DATE(s.showTime) = :date")
    List<Show> findByMovieIdAndTheatreCityAndDate(@Param("movieId") Long movieId,
                                                  @Param("city") String city,
                                                  @Param("date") LocalDate date);
}