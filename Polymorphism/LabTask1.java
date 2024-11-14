import java.util.*;

class Package {
    protected String senderName;
    protected String recipientName;
    protected String senderAddress;
    protected String recipientAddress;
    protected int weight;
    protected int costPerOunce;

    public Package(String senderName, String recipientName, String senderAddress, String recipientAddress, int weight, int costPerOunce) {
        if (weight < 0 || costPerOunce < 0) {
            throw new IllegalArgumentException("Weight and cost per ounce must be positive");
        }
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.weight = weight;
        this.costPerOunce = costPerOunce;
    }
    

    public double calculateCost() {
        return weight * costPerOunce;
    };

}

class TwoDayPackage extends Package {
    int flatFee;

    public TwoDayPackage(String senderName, String recipientName, String senderAddress, String recipientAddress,
            int weight, int costPerOunce, int flatfee) {
        super(senderName, recipientName, senderAddress, recipientAddress, weight, costPerOunce);
        this.flatFee = flatfee;
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() + flatFee;
    }
}

class OvernightPackage extends Package {
    int additionalFee;

    public OvernightPackage(String senderName, String recipientName, String senderAddress, String recipientAddress,
            int weight, int costPerOunce, int additionalFee) {
        super(senderName, recipientName, senderAddress, recipientAddress, weight, costPerOunce);
        this.additionalFee = additionalFee;
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() + additionalFee;
    }

}

public class LabTask1 {
    public static void main(String[] args) {
        Package package1 = new TwoDayPackage("Hasanat", "Ahmad", "Swabi", "ISB", 50, 4, 100);
        System.out.println(package1.calculateCost());

        Package package2 = new OvernightPackage("Hasanat", "Ahmad", "Swabi", "ISB", 50, 4, 150);
        System.out.println("Sender's Name: " + package2.senderName + "Sender's Address: " + package2.senderAddress +" Recipient's Name: " + package2.recipientName +" Recipient's Name: " + package2.recipientAddress+ "Total Cost: " + package2.calculateCost());

        
    }
}