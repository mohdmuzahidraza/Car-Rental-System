package com.carrental.controller;

import com.carrental.model.Customer;
import com.carrental.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo) { this.repo = repo; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", repo.findAll());
        return "customers";
    }

    @PostMapping("/add")
    public String add(Customer c) {
        repo.save(c);
        return "redirect:/customers";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/customers";
    }
}
