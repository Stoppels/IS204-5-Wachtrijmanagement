package spring.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User implements Serializable {

    private long id;
    
    @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters long.")
    private String name;

    @Size(min = 1, message = "StreetAdress is required.")
    private String streetAddress;

    @Size(min = 1, message = "City is required.")
    private String city;

    @Size(min = 1, message = "Housenumber is required.")
    private String houseNumber;

    private Role role;

    // constructors
    public User() {
        this.setId(System.nanoTime());
    }

    public User(long id, String name, String streetAddress, String city, String houseNumber, Role role) {

        this.setId(id);
        this.setName(name);
        this.setStreetAddress(streetAddress);
        this.setCity(city);
        this.setHouseNumber(houseNumber);
        this.setRole(role);

    }

    /* Getters en setters voor de verschillende attributen van het Model */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
