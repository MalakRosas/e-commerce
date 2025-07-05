package src;

public class Customer {
  private String name;
  private double balance;
  private Cart cart;

  public Customer(String name, double balance) {
    this.name = name;
    this.balance = balance;
    this.cart = new Cart();
  }

  public Cart getCart() {
    return cart;
  }

  public double getBalance() {
    return balance;
  }

  public void debit(double amount) {
    if (balance >= amount) {
      balance -= amount;
    } else {
      throw new IllegalArgumentException("Insufficient balance for customer: " + name);
    }
  }
}
