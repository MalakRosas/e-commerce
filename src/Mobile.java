package src;

public class Mobile extends Product implements Shippable {
  private double weight;

  public Mobile(String name, double price, int quantity, double weight) {
    super(name, price, quantity);
    this.weight = weight;
  }

  @Override
  public double getWeight() {
    return weight;
  }
}
