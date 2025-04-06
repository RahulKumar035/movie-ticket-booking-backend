package com.niraj.movieticketbooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieTicketBookingApplication {
    private static final Logger logger = LoggerFactory.getLogger(MovieTicketBookingApplication.class);

    public static void main(String[] args) {
        logger.info("DB_URL from env: {}", System.getenv("DB_URL"));
        logger.info("DB_USERNAME from env: {}", System.getenv("DB_USERNAME"));
        logger.info("DB_PASSWORD from env: {}", System.getenv("DB_PASSWORD"));
        SpringApplication.run(MovieTicketBookingApplication.class, args);
    }
}