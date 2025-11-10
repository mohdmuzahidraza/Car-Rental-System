package com.carrental.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    private Long customerId;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Rental() {}
    public Rental(Long carId, Long customerId, LocalDate rentDate) { this.carId = carId; this.customerId = customerId; this.rentDate = rentDate; }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getCarId() { return carId; } public void setCarId(Long carId) { this.carId = carId; }
    public Long getCustomerId() { return customerId; } public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public LocalDate getRentDate() { return rentDate; } public void setRentDate(LocalDate rentDate) { this.rentDate = rentDate; }
    public LocalDate getReturnDate() { return returnDate; } public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
