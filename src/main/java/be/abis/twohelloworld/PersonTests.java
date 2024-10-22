package be.abis.twohelloworld;

import be.abis.twohelloworld.model.Address;
import be.abis.twohelloworld.model.Company;
import be.abis.twohelloworld.model.Person;

public class PersonTests {
    public static void main(String[] args) {
        Address address = new Address("Leuven", "3000", "23", "Brusselsesteenweg");

        Company company = new Company("Abis", "+32472123456", "4234535348574384579", address);

        Person person = new Person(1,"John", "Doe", 35, "john.doe@abis.be", "34rFwe4£$R3ddr!", "en-INT",company);

        person.setCompany(company);

        System.out.println(person.toString());

        System.out.println(
                "\"" + person.getFirstName() + " " + person.getLastName()
                        + " is " + person.getAge() + " old and works for "
                        + person.getCompany().getName() + " in " + person.getCompany().getAddress().getTown()
                        + "\""
        );
    }
}
