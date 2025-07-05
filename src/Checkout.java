package src;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
  private double shippingRatePerKg;
  private ShippingService shippingService;

  public Checkout(double shippingRatePerKg, ShippingService shippingService) {
    this.shippingRatePerKg = shippingRatePerKg;
    this.shippingService = shippingService;
  }

  public void checkout(Customer customer) {
    Cart cart = customer.getCart();

    if (cart.isEmpty()) {
      throw new IllegalStateException("Cart is empty. Cannot proceed to checkout.");
    }

    double subtotal = 0.0;
    List<Shippable> shippables = new ArrayList<>();
    for (CartItem item : cart.getItems()) {
      Product product = item.getProduct();

      if (product instanceof Expirable) {
        Expirable exp = (Expirable) product;
        if (exp.isExpired()) {
          throw new IllegalStateException(product.getName() + " is expired.");
        }
      }

      if (product instanceof Shippable) {
        for (int i = 0; i < item.getQuantity(); i++) {
          shippables.add((Shippable) product);
        }
      }

      subtotal += item.subtotal();
    }

    double totalWeight = 0.0;
    for (Shippable s : shippables) {
      totalWeight += s.getWeight();
    }
    double shippingFee = totalWeight * shippingRatePerKg;
    double totalAmount = subtotal + shippingFee;

    if (customer.getBalance() < totalAmount) {
      throw new IllegalStateException("Customer does not have enough balance to complete the order.");
    }

    if (!shippables.isEmpty()) {
      shippingService.ship(shippables);
    }

    customer.debit(totalAmount);

    System.out.println("** Checkout receipt **");
    for (CartItem item : cart.getItems()) {
      System.out.printf("%dx %s %.2f\n", item.getQuantity(), item.getProduct().getName(), item.subtotal());
    }
    System.out.println("----------------------");
    System.out.printf("Subtotal %.2f\n", subtotal);
    System.out.printf("Shipping %.2f\n", shippingFee);
    System.out.printf("Amount %.2f\n", totalAmount);
    System.out.printf("Remaining Balance %.2f\n", customer.getBalance());
  }
}
