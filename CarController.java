package com.carrental.controller;

import com.carrental.model.Car;
import com.carrental.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarRepository repo;
    public CarController(CarRepository repo) { this.repo = repo; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cars", repo.findAll());
        return "cars";
    }

    @PostMapping("/add")
    public String add(Car car) {
        if (car.getAmountPerDay() == null) car.setAmountPerDay(0.0);
        car.setAvailable(true);
        repo.save(car);
        return "redirect:/cars";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/cars";
    }
}
