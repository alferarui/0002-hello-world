package be.abis.twohelloworld.model;

import java.util.Objects;

public class Company {
    String name;
    String telephoneNumber;
    String vatNr;
    Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getVatNr() {
        return vatNr;
    }

    public void setVatNr(String vatNr) {
        this.vatNr = vatNr;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(telephoneNumber, company.telephoneNumber) && Objects.equals(vatNr, company.vatNr) && Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, telephoneNumber, vatNr, address);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", vatNr='" + vatNr + '\'' +
                ", address=" + address +
                '}';
    }
}
