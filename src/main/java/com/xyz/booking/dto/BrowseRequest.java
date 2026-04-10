package com.xyz.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class BrowseRequest {
    @NotNull private Long movieId;
    @NotBlank private String city;
    @NotNull private LocalDate date;

    // Getters and Setters
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}