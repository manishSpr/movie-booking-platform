package com.xyz.booking.service;

import com.xyz.booking.dto.BrowseResponse;
import com.xyz.booking.entity.Movie;
import com.xyz.booking.entity.Show;
import com.xyz.booking.enums.SeatStatus;
import com.xyz.booking.repository.MovieRepository;
import com.xyz.booking.repository.SeatInventoryRepository;
import com.xyz.booking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ShowBrowseService {

    @Autowired private ShowRepository showRepository;
    @Autowired private MovieRepository movieRepository;
    @Autowired private SeatInventoryRepository seatRepository;

    public BrowseResponse browseShows(Long movieId, String city, LocalDate date) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        List<Show> shows = showRepository.findByMovieIdAndTheatreCityAndDate(movieId, city, date);

        java.util.Map<Long, BrowseResponse.TheatreShowDto> theatreMap = new java.util.HashMap<>();

        for (Show show : shows) {
            long availableSeats = seatRepository.countByIdShowIdAndStatus(show.getId(), SeatStatus.AVAILABLE);
            boolean isAfternoon = show.getShowTime().getHour() >= 12 && show.getShowTime().getHour() < 16;
            String discountMsg = isAfternoon ? "20% off (afternoon show)" : "None";

            BrowseResponse.ShowDto showDto = new BrowseResponse.ShowDto();
            showDto.setShowId(show.getId());
            showDto.setTiming(show.getShowTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            showDto.setDiscountApplied(discountMsg);
            showDto.setAvailableSeats(availableSeats);
            showDto.setPricePerSeat(show.getPricePerSeat());

            Long theatreId = show.getTheatre().getId();
            BrowseResponse.TheatreShowDto theatreDto = theatreMap.get(theatreId);
            if (theatreDto == null) {
                theatreDto = new BrowseResponse.TheatreShowDto();
                theatreDto.setTheatreId(theatreId);
                theatreDto.setName(show.getTheatre().getName());
                theatreDto.setCity(show.getTheatre().getCity());
                theatreDto.setShows(new ArrayList<>());
                theatreMap.put(theatreId, theatreDto);
            }
            theatreDto.getShows().add(showDto);
        }

        return new BrowseResponse(movie, new ArrayList<>(theatreMap.values()));
    }
}