    # Car Rental System v2 (fixed)

This project is a ready-to-run Spring Boot application.

## Run in IntelliJ
1. Open the project (File -> Open) and select the folder containing `pom.xml`.
2. Set Project SDK to Java 17.
3. Run `CarRentalSystemApplication` main class.
4. Open http://localhost:8080 in your browser.

Notes:
- Car images are stored in `src/main/resources/static/images/` and are used by filename when adding cars.
- Templates use `carsMap` and `custMap` to safely lookup names inside rentals to avoid NullPointer/Thymeleaf errors.
