package be.abis.twohelloworld;

import be.abis.twohelloworld.model.Address;
import be.abis.twohelloworld.model.Company;
import be.abis.twohelloworld.model.Person;

public class PersonTests {
    public static void main(String[] args) {
        Address address=new Address();
            address.setTown("Leuven");
            address.setZipCode("3000");
            address.setNr("23");
            address.setStreet("Brusselsesteenweg");

        Company company=new Company();
            company.setName("Abis");
            company.setTelephoneNumber("+32472123456");
            company.setVatNr("4234535348574384579");
            company.setAddress(address);

        Person person = new Person();
            person.setFirstName("John");
            person.setLastName("Doe");
            person.setAge(35);
            person.setPersonId(1);
            person.setEmailAddress("john.doe@abis.be");
            person.setPassword("34rFwe4£$R3ddr!");
            person.setLanguage("en-INT");

            person.setCompany(company);

        System.out.println(person.toString());

        System.out.println(
                "\"" + person.getFirstName() + " " + person.getLastName()
                        + " is " + person.getAge() + " old and works for "
                        + person.getCompany().getName()+" in " + person.getCompany().getAddress().getTown()
                        + "\""
        );
    }
}
