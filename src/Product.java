package src;

public class Product {
  private String name;
  private double price;
  private int quantity;

  public Product(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public boolean isAvailable(int requestedQty) {
    return quantity >= requestedQty;
  }

  public void reduceQuantity(int qty) {
    if (isAvailable(qty)) {
      quantity -= qty;
    } else {
      throw new IllegalArgumentException("Insufficient stock for " + name);
    }
  }
}
