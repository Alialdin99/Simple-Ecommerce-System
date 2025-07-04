package Models;

import java.time.LocalDate;
import java.util.*;

public class Cart {

    private Map<Product, Integer> items = new HashMap<>();
    private double totalPrice;

    public Cart() {
        this.items = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public void validateItem(Product product, int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        int alreadyInCart = items.getOrDefault(product, 0);

        if(quantity + alreadyInCart > product.getQuantity()) {
            throw new IllegalArgumentException("Quantity of "+ product.getName()+" exceeds available stock.");
        }

        if( product.getExpiryDate()!= null && product.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Product "+ product.getName()+" is expired.");
        }
    }
    public void add(Product product, int quantity) {
        validateItem(product, quantity);
        int alreadyInCart = items.getOrDefault(product, 0);

        items.put(product, alreadyInCart + quantity);
       product.quantity -= quantity;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Set<Map.Entry<Product, Integer>> getItems() {
        return items.entrySet();
    }

    public double getTotalPrice() {
        totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }
}
