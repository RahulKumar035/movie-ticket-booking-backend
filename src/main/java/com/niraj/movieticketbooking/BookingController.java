package com.niraj.movieticketbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingRequest bookingRequest, Authentication authentication) {
        String username = authentication.getName();
        Movie movie = movieRepository.findById(bookingRequest.getMovieId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        int bookedTickets = movie.getBookings().stream().mapToInt(Booking::getNumberOfTickets).sum();
        int requestedTickets = bookingRequest.getNumberOfTickets();
        int totalTickets = movie.getTotalTickets();
        if (bookedTickets + requestedTickets > totalTickets) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Not enough tickets available. Remaining: " + (totalTickets - bookedTickets));
        }
        Booking booking = new Booking(movie, username, requestedTickets);
        Booking savedBooking = bookingRepository.save(booking);
        int remainingTickets = totalTickets - (bookedTickets + requestedTickets);
        MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getTitle(), movie.getGenre(), 
                                        totalTickets, remainingTickets);
        return new BookingDTO(savedBooking.getId(), movieDTO, savedBooking.getCustomerName(), 
                             savedBooking.getNumberOfTickets());
    }

    @GetMapping
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream().map(booking -> {
            Movie movie = booking.getMovie();
            int bookedTickets = movie.getBookings().stream().mapToInt(Booking::getNumberOfTickets).sum();
            int remainingTickets = movie.getTotalTickets() - bookedTickets;
            MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getTitle(), movie.getGenre(), 
                                            movie.getTotalTickets(), remainingTickets);
            return new BookingDTO(booking.getId(), movieDTO, booking.getCustomerName(), 
                                 booking.getNumberOfTickets());
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id, Authentication authentication) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
        String username = authentication.getName();
        if (!booking.getCustomerName().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own bookings");
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-bookings")
    public List<BookingDTO> getMyBookings(Authentication authentication) {
        String username = authentication.getName();
        return bookingRepository.findByCustomerName(username).stream().map(booking -> {
            Movie movie = booking.getMovie();
            int bookedTickets = movie.getBookings().stream().mapToInt(Booking::getNumberOfTickets).sum();
            int remainingTickets = movie.getTotalTickets() - bookedTickets;
            MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getTitle(), movie.getGenre(), 
                                            movie.getTotalTickets(), remainingTickets);
            return new BookingDTO(booking.getId(), movieDTO, booking.getCustomerName(), 
                                 booking.getNumberOfTickets());
        }).collect(Collectors.toList());
    }
}

class BookingRequest {
    private Long movieId;
    private int numberOfTickets;

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
}