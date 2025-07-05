package src;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Case 1: Successful Checkout");
        try {
            Cheese cheese = new Cheese("Cheese", 100.0, 5, LocalDate.now().plusDays(5), 0.4);
            Biscuit biscuit = new Biscuit("Biscuit", 150.0, 3, LocalDate.now().plusDays(3), 0.7);
            MobileScratchCard scratchCard = new MobileScratchCard("Scratch Card", 50.0, 10);

            Customer customer = new Customer("Malak", 1000.0);
            Cart cart = customer.getCart();
            cart.add(cheese, 2);
            cart.add(biscuit, 1);
            cart.add(scratchCard, 1);

            ShippingService shippingService = new ShippingService();
            Checkout checkout = new Checkout(20.0, shippingService);
            checkout.checkout(customer);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nCase 2: Expired Product");
        try {
            Cheese expiredCheese = new Cheese("Expired Cheese", 100.0, 2, LocalDate.now().minusDays(1), 0.4);
            Customer customer = new Customer("Sarah", 1000.0);
            customer.getCart().add(expiredCheese, 1);

            new Checkout(20.0, new ShippingService()).checkout(customer);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nCase 3: Empty Cart");
        try {
            Customer customer = new Customer("Ali", 1000.0);
            new Checkout(20.0, new ShippingService()).checkout(customer);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nCase 4: Out of Stock Product");
        try {
            TV tv = new TV("TV", 300.0, 1, 10.0); 
            Customer customer = new Customer("Nour", 1000.0);
            customer.getCart().add(tv, 2);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nCase 5: Insufficient Balance");
        try {
            Cheese cheese = new Cheese("Cheese", 100.0, 3, LocalDate.now().plusDays(5), 0.4);
            TV tv = new TV("TV", 500.0, 1, 5.0);

            Customer customer = new Customer("Omar", 100.0);
            customer.getCart().add(cheese, 1);
            customer.getCart().add(tv, 1);
            new Checkout(20.0, new ShippingService()).checkout(customer);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
