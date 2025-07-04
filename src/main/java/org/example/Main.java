package org.example;

import Models.Cart;
import Models.Customer;
import Models.Product;
import Services.CheckOutService;
import Services.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 50, 10, 0.5, LocalDate.of(2025, 7, 1));   // Shippable + Expirable
        Product biscuits = new Product("Biscuits", 30, 15, 0.7, LocalDate.of(2025, 8, 15)); // Shippable + Expirable
        Product tv = new Product("TV", 3000, 2, 8.0, null);                                // Shippable only
        Product scratchCard = new Product("Scratch Card", 20, 100, null, null);            // Neither
        Product milk = new Product("Milk", 40, 5, null, LocalDate.of(2025, 7, 25));        // Expirable only
        Product sofa = new Product("Sofa", 5000, 1, 50.0, null);                           // Shippable only

        Customer customer = new Customer(15000);

        Cart cart = new Cart();
        try {
            cart.add(cheese, 2);
//            cart.add(biscuits, 1);
//            cart.add(tv, 1);
//            cart.add(scratchCard, 5);
//            cart.add(milk, 1);
//            cart.add(sofa, 1);
        } catch (Exception e) {
            System.out.println("Error while adding product to cart: " + e.getMessage());
            return;
        }

        ShippingService shippingService = new ShippingService();
        CheckOutService checkOutService = new CheckOutService(shippingService);

        try {
            checkOutService.checkOut(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout Failed: " + e.getMessage());
        }
    }



}