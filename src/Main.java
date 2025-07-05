package src;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {
    Cheese cheese = new Cheese("Cheese", 100.0, 5, LocalDate.now().plusDays(5), 0.4);
    Biscuit biscuit = new Biscuit("Biscuit", 150.0, 3, LocalDate.now().plusDays(3), 0.7);
    TV tv = new TV("TV", 300.0, 2, 10.0);
    Mobile mobile = new Mobile("Mobile", 200.0, 4, 0.2);
    MobileScratchCard scratchCard = new MobileScratchCard("Scratch Card", 50.0, 10);

    Customer customer = new Customer("Malak", 1000.0);

    Cart cart = customer.getCart();
    cart.add(cheese, 2);
    cart.add(biscuit, 1);
    cart.add(scratchCard, 1);

    ShippingService shippingService = new ShippingService();
    Checkout checkout = new Checkout(20.0, shippingService);

    try {
      checkout.checkout(customer);
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }
}
