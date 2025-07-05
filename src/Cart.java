package src;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  private List<CartItem> items = new ArrayList<>();

  public void add(Product product, int quantity) {
    if (!product.isAvailable(quantity)) {
      throw new IllegalArgumentException("Not enough stock for: " + product.getName());
    }

    product.reduceQuantity(quantity);
    items.add(new CartItem(product, quantity));
  }

  public List<CartItem> getItems() {
    return items;
  }

  public double subtotal() {
    double total = 0.0;
    for (CartItem item : items) {
      total += item.subtotal();
    }
    return total;
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }
}
