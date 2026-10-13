# ShopNest test checklist

| # | Test | Steps | Expected result |
|---|---|---|---|
| 1 | Home page | Open `/` | Sample products and categories appear |
| 2 | Search match | Search `headphones` | Headphones product appears |
| 3 | Search no match | Search `xyznotaproduct` | Friendly no-results state appears |
| 4 | Category filter | Select Fashion | Only Fashion products appear |
| 5 | Product details | Open a product card | Correct image, name, description and price appear |
| 6 | Add to bag | Click `+` or Add to bag | Cart contains the product |
| 7 | Quantity change | Set a cart quantity to 2 and update | Quantity and total change |
| 8 | Remove item | Set quantity to 0 and update | Item disappears |
| 9 | Registration validation | Try a short password | Clear validation error appears |
| 10 | Duplicate account | Register the same email twice | Duplicate email error appears |
| 11 | Login | Use correct credentials | User is signed in |
| 12 | Bad login | Use incorrect credentials | Generic login error appears |
| 13 | Checkout guest | Add item, then checkout while logged out | Redirects to login |
| 14 | Checkout validation | Submit a short address | Validation message appears |
| 15 | Successful order | Sign in, add item, enter valid demo address | Confirmation with order number and total appears |
| 16 | Order privacy | While signed in, try another account's order ID | Redirected to home |
| 17 | Restart persistence | Stop and restart app | Product and order records remain in H2 file database |
