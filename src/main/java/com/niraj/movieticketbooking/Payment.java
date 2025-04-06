package com.niraj.movieticketbooking;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(name = "payment_intent_id")
    private String paymentIntentId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        PENDING, COMPLETED, FAILED
    }

    // Constructors
    public Payment() {}
    public Payment(Booking booking, double amount, Status status, String paymentIntentId) {
        this.booking = booking;
        this.amount = amount;
        this.status = status;
        this.paymentIntentId = paymentIntentId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getPaymentIntentId() { return paymentIntentId; }
    public void setPaymentIntentId(String paymentIntentId) { this.paymentIntentId = paymentIntentId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}