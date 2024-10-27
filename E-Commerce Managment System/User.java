public class User extends Person {
    private Cart cart;

    User(Cart carts, String name, String email, String password, Address address) {
        super(name, email, password, address);
        this.cart = carts;
    }

    public void setCart(Cart carts) {
        this.cart = carts;
    }

    public Cart getCart() {
        return cart;
    }
    
}
