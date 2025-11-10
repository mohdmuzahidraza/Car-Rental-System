package com.carrental.controller;

import com.carrental.model.Car;
import com.carrental.model.Customer;
import com.carrental.model.Rental;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.RentalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rentals")
public class RentalController {
    private final RentalRepository rentalRepo;
    private final CarRepository carRepo;
    private final CustomerRepository custRepo;

    public RentalController(RentalRepository rentalRepo, CarRepository carRepo, CustomerRepository custRepo) {
        this.rentalRepo = rentalRepo; this.carRepo = carRepo; this.custRepo = custRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rentals", rentalRepo.findAll());
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("customers", custRepo.findAll());
        // also add maps for safe lookup in thymeleaf
        Map<Long, Car> carsMap = carRepo.findAll().stream().collect(Collectors.toMap(Car::getId, c -> c));
        Map<Long, Customer> custMap = custRepo.findAll().stream().collect(Collectors.toMap(Customer::getId, c -> c));
        model.addAttribute("carsMap", carsMap);
        model.addAttribute("custMap", custMap);
        return "rentals";
    }

    @PostMapping("/rent")
    public String rent(@RequestParam Long carId, @RequestParam Long customerId) {
        Car car = carRepo.findById(carId).orElse(null);
        Customer cust = custRepo.findById(customerId).orElse(null);
        if (car != null && cust != null && car.isAvailable()) {
            car.setAvailable(false);
            carRepo.save(car);
            rentalRepo.save(new Rental(carId, customerId, LocalDate.now()));
        }
        return "redirect:/rentals";
    }

    @PostMapping("/return")
    public String returnCar(@RequestParam Long id) {
        Rental r = rentalRepo.findById(id).orElse(null);
        if (r != null && r.getReturnDate() == null) {
            r.setReturnDate(LocalDate.now());
            rentalRepo.save(r);
            Car car = carRepo.findById(r.getCarId()).orElse(null);
            if (car != null) { car.setAvailable(true); carRepo.save(car); }
        }
        return "redirect:/rentals";
    }
}
