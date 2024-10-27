public class Cart {
    private Item[] items;

    public Cart() {
        items = new Item[10];
    }

    public Item[] getItems() {
        return items;
    }

    public void addItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
    }

    public void removeItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName() == item.getName()) {
                items[i] = null;
                break;
            }
        }
    }

    public void displayCart() {
        boolean hasItem = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                items[i].displayItems();
                hasItem = true;
            }
        }
        if (!hasItem) {
            System.out.println("No item in the cart");
        }
    }
}
