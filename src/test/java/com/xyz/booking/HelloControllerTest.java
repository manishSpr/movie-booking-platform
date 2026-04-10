//package com.xyz.booking;
//
//import com.xyz.booking.entity.*;
//import com.xyz.booking.enums.SeatStatus;
//import com.xyz.booking.repository.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.LocalDate;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ShowBrowseIntegrationTest {
//
//    @Container
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
//            .withDatabaseName("testdb")
//            .withUsername("test")
//            .withPassword("test");
//
//    @DynamicPropertySource
//    static void properties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//        registry.add("spring.flyway.enabled", () -> "true");
//    }
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TheatreRepository theatreRepository;
//    @Autowired
//    private MovieRepository movieRepository;
//    @Autowired
//    private ShowRepository showRepository;
//    @Autowired
//    private SeatInventoryRepository seatRepository;
//
//    private Long movieId;
//    private Long theatreId;
//    private Long showId;
//
//    @BeforeEach
//    void setUp() {
//        // Clean up
//        seatRepository.deleteAll();
//        showRepository.deleteAll();
//        movieRepository.deleteAll();
//        theatreRepository.deleteAll();
//
//        // Create theatre
//        Theatre theatre = new Theatre();
//        theatre.setName("Test Theatre");
//        theatre.setCity("Mumbai");
//        theatre.setAddress("123 Test St");
//        theatre.setIntegrationType("MANUAL");
//        theatre = theatreRepository.save(theatre);
//        theatreId = theatre.getId();
//
//        // Create movie
//        Movie movie = new Movie();
//        movie.setName("Test Movie");
//        movie.setLanguage("Hindi");
//        movie.setGenre("Action");
//        movie = movieRepository.save(movie);
//        movieId = movie.getId();
//
//        // Create show
//        Show show = new Show();
//        show.setTheatre(theatre);
//        show.setMovie(movie);
//        show.setShowTime(LocalDateTime.of(2026, 4, 15, 14, 30)); // afternoon show
//        show.setPricePerSeat(BigDecimal.valueOf(250));
//        show.setTotalSeats(100);
//        show = showRepository.save(show);
//        showId = show.getId();
//
//        // Create seat inventory (A1..A10)
//        for (int i = 1; i <= 10; i++) {
//            SeatInventory seat = new SeatInventory();
//            seat.setId(new SeatInventoryId(showId, "A" + i));
//            seat.setStatus(SeatStatus.AVAILABLE);
//            seatRepository.save(seat);
//        }
//    }
//
//    @Test
//    void browseShows_ShouldReturnCorrectData() {
//        given()
//                .port(port)
//                .queryParam("movieId", movieId)
//                .queryParam("city", "Mumbai")
//                .queryParam("date", "2026-04-15")
//                .when()
//                .get("/api/v1/shows")
//                .then()
//                .statusCode(200)
//                .body("movie.id", is(movieId.intValue()))
//                .body("movie.name", is("Test Movie"))
//                .body("theatres[0].name", is("Test Theatre"))
//                .body("theatres[0].shows[0].discountApplied", is("20% off (afternoon show)"))
//                .body("theatres[0].shows[0].availableSeats", is(10))
//                .body("theatres[0].shows[0].pricePerSeat", is(250.0f));
//    }
//}