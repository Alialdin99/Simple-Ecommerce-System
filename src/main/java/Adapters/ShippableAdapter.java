package Adapters;
import Interface.Shippable;
import Models.Product;

public class ShippableAdapter implements Shippable {
    private Product product;
    private int quantity;
    public ShippableAdapter(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }


    @Override
    public double getWeight() {
        return product.getWeight();
    }
}
