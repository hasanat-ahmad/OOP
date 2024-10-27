public class Admin extends Person {
    String accountNo;

    Admin(String name, String email, String password, Address address, String accountNo) {
        super(name, email, password, address);
        this.accountNo = accountNo;

    }

    public void setAccountNo(String accoutnNo) {
        this.accountNo = accoutnNo;
    }

    public String getAccountNo() {
        return accountNo;
    }
}
