package com.niraj.movieticketbooking;

public class MovieDTO {
    private Long id;
    private String title;
    private String genre;
    private int totalTickets;
    private int remainingTickets;

    public MovieDTO(Long id, String title, String genre, int totalTickets, int remainingTickets) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.totalTickets = totalTickets;
        this.remainingTickets = remainingTickets;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }
    public int getRemainingTickets() { return remainingTickets; }
    public void setRemainingTickets(int remainingTickets) { this.remainingTickets = remainingTickets; }
}