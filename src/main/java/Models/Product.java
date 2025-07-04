package Models;
import java.time.LocalDate;

public class Product {
    public String name;
    public double price;
    public int quantity;
    private Double weight;
    private LocalDate expiryDate;

    public Product(String name, double price, int quantity, Double weight, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.expiryDate = expiryDate;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Double getWeight() { return weight; }
    public LocalDate getExpiryDate() { return expiryDate; }
}
