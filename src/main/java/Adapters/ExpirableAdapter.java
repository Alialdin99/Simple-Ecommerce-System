package Adapters;
import Interface.Expirable;
import Models.Product;

import java.time.LocalDate;

public class ExpirableAdapter implements Expirable {
    private Product product;

    public ExpirableAdapter(Product product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public LocalDate getExpiryDate() {
        return product.getExpiryDate();
    }

    @Override
    public boolean isExpired() {
        return product.getExpiryDate().isBefore(LocalDate.now());
    }
}
