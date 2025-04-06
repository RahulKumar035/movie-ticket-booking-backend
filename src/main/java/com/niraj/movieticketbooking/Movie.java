package com.niraj.movieticketbooking;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private int totalTickets;
    private String posterPath; // New field for TMDB poster path

    @OneToMany(mappedBy = "movie")
    private List<Booking> bookings;

    // Default constructor (required by JPA)
    public Movie() {}

    // Corrected constructor (removed id)
    public Movie(String title, String genre, int totalTickets, String posterPath) {
        this.title = title;
        this.genre = genre;
        this.totalTickets = totalTickets;
        this.posterPath = posterPath;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }
    public String getPosterPath() { return posterPath; }
    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
}