# ShopNest — Viva preparation

## 30-second introduction
ShopNest is a Java-based shopping web application developed using Spring Boot, Thymeleaf, Spring Data JPA and an embedded H2 database. Users can search and filter products, view details, manage a shopping cart, register or log in, and place a demo order. The project uses server-side routes and stores customer, product and order data in a local database. It does not process real payments.

## Common questions and simple answers

**1. Why did you choose Spring Boot?**  
It reduces setup work, configures the embedded web server and makes it easier to organise a Java web application.

**2. What is Thymeleaf?**  
Thymeleaf is a server-side template engine. It combines HTML templates with data sent by Java controllers.

**3. What is Spring Data JPA?**  
It simplifies database operations using repositories, so common operations such as saving and finding records need less boilerplate code.

**4. Which database does the project use?**  
H2, an embedded relational database. The file database is stored in the project's `data` folder, so a separate database installation is not needed.

**5. How does search work?**  
The browser sends the keyword as a GET request to `/`. The `StoreController` reads it, and `ProductRepository.search()` runs a database query matching product name, description or category. Results are rendered by `index.html`.

**6. How does the cart work?**  
The cart is stored in the user's HTTP session as a map of product IDs and quantities. The server calculates each line subtotal and the total from database product prices.

**7. What is a session?**  
A session stores data associated with a visitor between HTTP requests. This project uses it to keep the cart and the signed-in customer's ID.

**8. How are passwords stored?**  
The application stores BCrypt password hashes rather than plain-text passwords. During login, BCrypt checks the entered password against the saved hash.

**9. What happens at checkout?**  
A signed-in customer submits an address. The server validates it, creates an order and order-item records, saves them in the database, clears the cart and shows an order confirmation.

**10. What is validation?**  
Validation checks input before processing it. This project checks required form fields, email format, minimum password length, address length and cart quantities.

**11. What are the main classes?**  
`Product`, `Customer`, `ShopOrder` and `OrderItem` are entities. Controllers handle web requests, repositories access data, and services contain application rules.

**12. What are the limitations?**  
It is a local academic prototype. It does not process payments, send emails, ship goods or include admin inventory management. A production version needs additional security controls, payment integration and a production database.

## Be ready to demonstrate
1. Search for `headphones`.
2. Filter by a category.
3. Open a product and add it to the bag.
4. Change quantity and update the total.
5. Register a demo account and log in.
6. Checkout with a fake address and show the saved order confirmation.
7. Show the H2 console and explain the tables.

Use a demo password you do not use for any other account. Do not enter real payment information.
