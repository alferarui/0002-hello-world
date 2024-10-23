package be.abis.twohelloworld.model;

import be.alferarui.csvmagic.MagicCsvField;
import be.alferarui.csvmagic.MagicCsvId;

import java.util.Objects;


public class Address {
    @MagicCsvId
    private String street;
    @MagicCsvId
    private String nr;
    @MagicCsvId
    private String zipCode;
    @MagicCsvField(serializer = "String.valueOf", deserializer="Long.parseLong")
    private Long addressId;
    private String town;

    public Address(){}

    public Address(Long addressId,String street, String nr, String zipCode, String town) {
        this.addressId = addressId;
        this.street = street;
        this.nr = nr;
        this.zipCode = zipCode;
        this.town = town;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

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
        return Objects.equals(addressId, address.addressId) && Objects.equals(street, address.street) && Objects.equals(nr, address.nr) && Objects.equals(zipCode, address.zipCode) && Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, nr, zipCode, town);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + getStreet() +
                ", street=" + ((getStreet()==null)?"null":('"' + getStreet() + '"'))  +
                ", nr=" + ((getNr()==null)?"null":('"' + getNr() + '"'))  +
                ", zipCode=" + ((getZipCode()==null)?"null":('"' + getZipCode() + '"'))  +
                ", town=" + ((getTown()==null)?"null":('"' + getTown() + '"'))  +
                '}';
    }
}
