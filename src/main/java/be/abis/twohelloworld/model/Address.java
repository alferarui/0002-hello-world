package be.abis.twohelloworld.model;

import java.util.Objects;

public class Address {
    String street;
    String nr;
    String zipCode;
    String town;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(nr, address.nr) && Objects.equals(zipCode, address.zipCode) && Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, nr, zipCode, town);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", nr='" + nr + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
