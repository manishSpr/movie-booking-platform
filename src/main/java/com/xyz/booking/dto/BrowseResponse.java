package com.xyz.booking.dto;

import com.xyz.booking.entity.Movie;
import java.util.List;

public class BrowseResponse {
    private MovieDto movie;
    private List<TheatreShowDto> theatres;

    public BrowseResponse(Movie movie, List<TheatreShowDto> theatres) {
        this.movie = new MovieDto(movie.getId(), movie.getName(), movie.getLanguage(), movie.getGenre());
        this.theatres = theatres;
    }
    // Getters and Setters
    public MovieDto getMovie() { return movie; }
    public void setMovie(MovieDto movie) { this.movie = movie; }
    public List<TheatreShowDto> getTheatres() { return theatres; }
    public void setTheatres(List<TheatreShowDto> theatres) { this.theatres = theatres; }

    public static class MovieDto {
        private Long id; private String name; private String language; private String genre;
        public MovieDto(Long id, String name, String language, String genre) {
            this.id = id; this.name = name; this.language = language; this.genre = genre;
        }
        // getters...
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getLanguage() { return language; }
        public String getGenre() { return genre; }
    }

    public static class TheatreShowDto {
        private Long theatreId; private String name; private String city; private List<ShowDto> shows;
        // getters/setters omitted for brevity – add them
        public Long getTheatreId() { return theatreId; }
        public void setTheatreId(Long theatreId) { this.theatreId = theatreId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public List<ShowDto> getShows() { return shows; }
        public void setShows(List<ShowDto> shows) { this.shows = shows; }
    }

    public static class ShowDto {
        private Long showId; private String timing; private String discountApplied; private Long availableSeats; private java.math.BigDecimal pricePerSeat;
        // getters/setters
        public Long getShowId() { return showId; }
        public void setShowId(Long showId) { this.showId = showId; }
        public String getTiming() { return timing; }
        public void setTiming(String timing) { this.timing = timing; }
        public String getDiscountApplied() { return discountApplied; }
        public void setDiscountApplied(String discountApplied) { this.discountApplied = discountApplied; }
        public Long getAvailableSeats() { return availableSeats; }
        public void setAvailableSeats(Long availableSeats) { this.availableSeats = availableSeats; }
        public java.math.BigDecimal getPricePerSeat() { return pricePerSeat; }
        public void setPricePerSeat(java.math.BigDecimal pricePerSeat) { this.pricePerSeat = pricePerSeat; }
    }
}
