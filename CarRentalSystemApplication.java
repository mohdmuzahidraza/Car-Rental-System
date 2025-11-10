package com.carrental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.carrental.model.Car;
import com.carrental.model.Customer;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CustomerRepository;

@SpringBootApplication
public class CarRentalSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarRentalSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(CarRepository carRepo, CustomerRepository custRepo) {
        return args -> {
            if (carRepo.count() == 0) {
                carRepo.save(new Car("Toyota Corolla","Sedan","corolla.png",2000.0,true));
                carRepo.save(new Car("Honda Civic","Sedan","civic.png",2200.0,true));
                carRepo.save(new Car("Ford Mustang","Sports","mustang.png",5000.0,true));
            }
            if (custRepo.count() == 0) {
                custRepo.save(new Customer("Alice","alice@example.com"));
                custRepo.save(new Customer("Bob","bob@example.com"));
            }
        };
    }
}
