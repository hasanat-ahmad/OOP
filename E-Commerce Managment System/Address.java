public class Address {
    private String streetNo;
    private String city;
    private String houseNo;

    Address(String streetNo,
            String city,
            String houseNo) {
        this.streetNo = streetNo;
        this.city = city;
        this.houseNo = houseNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public void printAddress() {
        System.out.println(streetNo + houseNo + city);
    }       
}
