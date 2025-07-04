package Services;

import Interface.Shippable;

import java.util.ArrayList;
import java.util.List;

public class ShippingService {
//    private List<Shippable> items = new ArrayList<>();
//    public void addToShipment(Shippable item) {
//        items.add(item);
//    }

    public double calculateTotalWeight(List<Shippable> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public double calculateShippingCost(List<Shippable> items) {
        double totalWeight = calculateTotalWeight(items);
        return totalWeight * 0.05; //$0.05 per gram
    }

    public void printShipmentDetails(List<Shippable> items) {
        double totalWeight = 0;

        System.out.println("** Shipment Notice **");
        for (Shippable item : items) {
            System.out.printf("%dx %s %.1f g%n", item.getQuantity(), item.getName(), item.getQuantity() * item.getWeight());
            totalWeight += item.getQuantity() * item.getWeight();
        }

        System.out.printf("Total package weight: %.1f g%n", totalWeight);
    }

}
