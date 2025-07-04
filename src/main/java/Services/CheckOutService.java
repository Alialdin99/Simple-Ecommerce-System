package Services;

import Interface.Shippable;
import Models.Cart;
import Models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CheckOutService {
    ShippingService shippingService;

    public CheckOutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }
    public void checkOut(Customer customer, Cart cart) {
        validateCheckout(customer, cart);

        double subtotal = cart.getTotalPrice();
        List<Shippable> shippableItems = collectShippableItems(cart);
        double shippingFee = calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.balance < totalAmount) {
            throw new IllegalArgumentException("Insufficient balance to complete the purchase.");
        }

        if (!shippableItems.isEmpty()) {
            shippingService.printShipmentDetails(shippableItems);
        }

        customer.balance -= totalAmount;
        printReceipt(cart, subtotal, shippingFee, totalAmount, customer);
    }

    private void validateCheckout(Customer customer, Cart cart) {
        if (cart.isEmpty())
            throw new IllegalArgumentException("Cart is empty. Cannot proceed to checkout.");
        if (customer == null)
            throw new IllegalArgumentException("Customer cannot be null.");
        if (customer.balance == 0)
            throw new IllegalArgumentException("Customer balance is not set.");
        if (customer.balance < cart.getTotalPrice())
            throw new IllegalArgumentException("Insufficient balance to complete the purchase.");
    }

    private List<Shippable> collectShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        for (var entry : cart.getItems()) {
            var product = entry.getKey();
            var quantity = entry.getValue();

            if (product.getWeight() != null) {
                var shippableProduct = new Adapters.ShippableAdapter(product,quantity);
                shippableItems.add(shippableProduct);
            }
        }
        return shippableItems;
    }

    private double calculateShippingFee(List<Shippable> shippableItems) {
        return shippingService.calculateShippingCost(shippableItems);
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount, Customer customer) {
        System.out.println("\n** Checkout Receipt **");
        for (var entry : cart.getItems()) {
            var product = entry.getKey();
            var quantity = entry.getValue();
            System.out.printf("%dx %s - %.2f%n", quantity, product.getName(), product.getPrice() * quantity);
        }
        System.out.printf("Subtotal: %.2f%n", subtotal);
        System.out.printf("Shipping: %.2f%n", shippingFee);
        System.out.printf("Total Amount: %.2f%n", totalAmount);
        System.out.printf("Remaining Balance: %d%n", customer.balance);
    }


}
