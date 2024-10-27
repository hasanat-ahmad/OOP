import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        Item item1 = new Item("Watch", "ABC", 9000);
        Item item2 = new Item("Clock", "EFG", 8000);
        Item item3 = new Item("Pen", "XYZ", 7000);

        System.out.println("1. Create a user account");
        System.out.println("2. Create a admin account");
        choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            // sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            // sc.nextLine();
            System.out.print("Enter street no: ");
            String streetNo = sc.nextLine();
            // sc.nextLine();
            System.out.print("Enter house no.: ");
            String houseNo = sc.nextLine();
            // sc.nextLine();
            System.out.print("Enter city: ");
            String city = sc.nextLine();
            // sc.nextLine();
            Address address = new Address(streetNo, city, houseNo);
            Cart cart = new Cart();

            User user1 = new User(cart, name, email, password, address);

            System.out.println("1. Add an item into the cart: ");
            System.out.println("2. Remove an item from the cart: ");

            int choiceForAction = sc.nextInt();

            if (choiceForAction == 1) {
                System.out.println(item1.getName() + " " + item1.getDescription() + " " + item1.getPrice());
                System.out.println(item2.getName() + " " + item2.getDescription() + " " + item2.getPrice());
                System.out.println(item3.getName() + " " + item3.getDescription() + " " + item3.getPrice());
                System.out.println("Which item to put in cart");
                System.out.print("Item 1 or Item 2 or Item 3: ");
                int itemToPutInCart = sc.nextInt();
                if (itemToPutInCart == 1) {
                    user1.getCart().addItem(item1);
                } else if (itemToPutInCart == 2) {
                    user1.getCart().addItem(item2);
                } else if (itemToPutInCart == 3) {
                    user1.getCart().addItem(item3);
                }
            } else if (choiceForAction == 2) {
                cart.displayCart();
                System.out.println("Which item to delete from cart");
                System.out.print("Item 1 or Item 2 or Item 3: ");
                int itemToBeDeleted = sc.nextInt();
                if (itemToBeDeleted == 1){
                    user1.getCart().removeItem(item1);
                }
                else if (itemToBeDeleted == 2){
                    user1.getCart().removeItem(item2);
                }
                else if (itemToBeDeleted == 3){
                    user1.getCart().removeItem(item3);
                }
            }

        } else if (choice == 2) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            System.out.print("Enter street no: ");
            String streetNo = sc.nextLine();
            System.out.print("Enter house no.: ");
            String houseNo = sc.nextLine();
            System.out.print("Enter city: ");
            String city = sc.nextLine();
            Address address = new Address(streetNo, city, houseNo);
            System.out.print("Enter account number: ");
            String accountNo = sc.nextLine();

            Admin admin = new Admin(name, email, password, address, accountNo);
        }
    }
}
