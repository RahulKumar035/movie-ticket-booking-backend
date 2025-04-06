package com.niraj.movieticketbooking;

public class BookingDTO {
    private Long id;
    private MovieDTO movie;
    private String customerName;
    private int numberOfTickets;

    public BookingDTO(Long id, MovieDTO movie, String customerName, int numberOfTickets) {
        this.id = id;
        this.movie = movie;
        this.customerName = customerName;
        this.numberOfTickets = numberOfTickets;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public MovieDTO getMovie() { return movie; }
    public void setMovie(MovieDTO movie) { this.movie = movie; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
}