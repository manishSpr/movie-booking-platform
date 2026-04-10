package com.xyz.booking.controller;

import com.xyz.booking.dto.BookingRequest;
import com.xyz.booking.dto.BookingResponse;
import com.xyz.booking.dto.BrowseRequest;
import com.xyz.booking.dto.BrowseResponse;
import com.xyz.booking.service.BookingService;
import com.xyz.booking.service.ShowBrowseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired private ShowBrowseService browseService;
    @Autowired private BookingService bookingService;

    @GetMapping("/shows")
    public BrowseResponse browseShows(@Valid @ModelAttribute BrowseRequest request) {
        return browseService.browseShows(request.getMovieId(), request.getCity(), request.getDate());
    }

    @PostMapping("/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookTickets(@Valid @RequestBody BookingRequest request) {
        return bookingService.bookTickets(request);
    }
}