package src;

import java.util.List;

public class ShippingService {
  public void ship(List<Shippable> items) {
    System.out.println("** Shipment notice **");
    double totalWeight = 0.0;

    for (Shippable item : items) {
      System.out.println("Shipping: " + item.getName() + " (" + item.getWeight() + "kg)");
      totalWeight += item.getWeight();
    }

    System.out.printf("Total package weight %.1fkg\n", totalWeight);
  }
}
