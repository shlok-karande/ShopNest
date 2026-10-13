# ShopNest — Java Shopping Web Application

ShopNest is a student-friendly online store built with Java 17, Spring Boot, Thymeleaf, Spring Data JPA and an embedded H2 database. It has its own branding and layout; it is inspired by common e-commerce patterns, not a copy of another store.

## Requirements
- JDK 17 or newer
- Maven 3.8+ (or use IntelliJ IDEA's Maven support)
- Internet once, so Maven can download project dependencies

**No separate database installation is needed.** H2 runs locally and saves data to `./data/shopnest.mv.db`.

## Run
1. Install JDK 17 and Maven.
2. Extract the ZIP.
3. Open a terminal in the `ShopNest` folder.
4. Run `mvn spring-boot:run`
5. Open `http://localhost:8080`

In IntelliJ: Open the folder as a Maven project, wait for dependencies, then run `ShopNestApplication.java`.

H2 console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/shopnest`
- User: `sa`
- Password: leave blank

## Main features
- Server-side keyword search and category filter
- Product detail pages
- Session cart with quantity updates and removal
- Registration and login with BCrypt password hashing
- Checkout with required-field validation
- Persisted order and order items
- Sample products automatically added on first run
- Responsive styling and accessible labels

## Demo flow
1. Search `headphones` and open a product.
2. Add it to cart; change quantity and see total update.
3. Register a demo account using an email you control (do not use a real password you use elsewhere).
4. Log in, checkout with a demo address, and show the order confirmation.
5. Search for a word that is not in the catalogue to show the empty state.

## Important project limitations
This is a local academic demo, not a production commerce system. It does not process real payments, send emails, calculate shipping, or include admin inventory management. Before public deployment, add CSRF protection, rate limiting, stronger session security, secure configuration, and a proper production database. Never enter real payment data.

## Package map
- `controller/` — web routes
- `model/` — JPA entities
- `repository/` — database queries
- `service/` — business rules and seed data
- `templates/` — server-rendered HTML
- `static/css/` — design styles
