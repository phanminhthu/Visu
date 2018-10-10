package danazone.com.visu.bean;

public class InfoUser {
    private int id;
    private String name;
    private String phone;
    private String homeNumber;
    private String street;
    private String country;
    private String city;
    private int type;
    private String email;
    private String avatar;

    public InfoUser() {

    }

    public InfoUser(int id, String name, String phone, String homeNumber, String street, String country, String city, int type, String email, String avatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.homeNumber = homeNumber;
        this.street = street;
        this.country = country;
        this.city = city;
        this.type = type;
        this.email = email;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
