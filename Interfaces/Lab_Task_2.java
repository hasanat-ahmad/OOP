interface Payable {
    public double getPaymentAmount();
}

class Invoice implements Payable {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    public Invoice() {
    }

    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }
}

class Employee {
    private String firstName;
    private String lastName;
    private String SSN;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String SSN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

}

class SalariedEmployee extends Employee implements Payable {
    private double weeklySalary;

    public SalariedEmployee() {
    }

    public SalariedEmployee(String firstName, String lastName, String SSN, double weeklySalary) {
        super(firstName, lastName, SSN);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double getPaymentAmount() {
        return weeklySalary;
    }
}

public class Lab_Task_2 {
    public static void main(String[] args) {
        Employee employee = new SalariedEmployee("Hasanat", "Ahamd", "STD123", 5000);
        SalariedEmployee employee2 = (SalariedEmployee)(employee);
        Invoice invoice = new Invoice("ABC", "XYZ", 5, 20);
        System.out.println("Salaried Employee: "+(employee2.getPaymentAmount()));
        System.out.println("Invoice: "+invoice.getPaymentAmount());
    }
}
