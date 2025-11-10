package com.carrental.controller;

import com.carrental.model.Car;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.RentalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final CarRepository carRepo;
    private final CustomerRepository custRepo;
    private final RentalRepository rentalRepo;

    public ReportController(CarRepository carRepo, CustomerRepository custRepo, RentalRepository rentalRepo) {
        this.carRepo = carRepo; this.custRepo = custRepo; this.rentalRepo = rentalRepo;
    }

    @GetMapping
    public String report(Model model) {
        long totalCars = carRepo.count();
        long availableCars = carRepo.findAll().stream().filter(Car::isAvailable).count();
        long totalCustomers = custRepo.count();
        long totalRentals = rentalRepo.count();
        long activeRentals = rentalRepo.findAll().stream().filter(r -> r.getReturnDate() == null).count();
        model.addAttribute("totalCars", totalCars);
        model.addAttribute("availableCars", availableCars);
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("totalRentals", totalRentals);
        model.addAttribute("activeRentals", activeRentals);
        return "reports";
    }
}
