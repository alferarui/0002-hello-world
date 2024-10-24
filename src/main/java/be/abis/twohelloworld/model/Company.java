package be.abis.twohelloworld.model;


import be.abis.csvmagic.MagicCsvField;
import be.abis.csvmagic.MagicCsvId;
import be.abis.csvmagic.MagicCsvIgnore;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class Company {
    @MagicCsvField(deserializer = "Long.parseLong",serializer = "Long.valueOf")
    private Long companyId;
    private String name;
    private String telephoneNumber;
    @MagicCsvId
    private String vatNr;
    @MagicCsvIgnore
    private Address address;

    public Company() {
        Address a=new Address();

    }

    public Company(Long companyId,String name, String telephoneNumber, String vatNr, Address address) {
        this.companyId = companyId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.vatNr = vatNr;
        this.address = address;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

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
        return Objects.equals(companyId, company.companyId) && Objects.equals(name, company.name) && Objects.equals(telephoneNumber, company.telephoneNumber) && Objects.equals(vatNr, company.vatNr) && Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, telephoneNumber, vatNr, address);
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId='" + getCompanyId() + '\'' +
                ", telephoneNumber='" + getTelephoneNumber() + '\'' +
                ", vatNr='" + getVatNr() + '\'' +
                ", name=" + ((getName()==null)?"null":('"' + getName() + '"'))  +
                ", telephoneNumber=" + ((getTelephoneNumber()==null)?"null":('"' + getTelephoneNumber() + '"'))  +
                ", vatNr=" + ((getVatNr()==null)?"null":('"' + getVatNr() + '"'))  +
                ", address=" + getAddress() +
                '}';
    }
}
