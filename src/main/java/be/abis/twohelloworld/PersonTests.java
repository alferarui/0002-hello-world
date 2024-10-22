package be.abis.twohelloworld;

import be.abis.twohelloworld.model.Address;
import be.abis.twohelloworld.model.Company;
import be.abis.twohelloworld.model.Instructor;
import be.abis.twohelloworld.model.Person;

import java.util.ArrayList;

public class PersonTests {
    public static void main(String[] args) {
        Address address = new Address("Leuven", "3000", "23", "Brusselsesteenweg");

        Company company = new Company("Abis", "+32472123456", "4234535348574384579", address);

        Person person = new Person("John", "Doe",company);

        person.setCompany(company);

        System.out.println(person.toString());

        System.out.println(
                "\"" + person.getFirstName() + " " + person.getLastName()
                        + " is " + person.getAge() + " old and works for "
                        + person.getCompany().getName() + " in " + person.getCompany().getAddress().getTown()
                        + "\""
        );


        ArrayList<Person> persons = new ArrayList<>(){{
            add(new Person("John", "Doe"));
            add(new Person("Jane", "Doe"));
            add(new Person("Betty", "Doe"));
            add(new Person("Paul", "Doe"));
            add(new Person("Dick", "Doe"));
            add(new Person("Bob", "Doe"));
        }};
        Instructor instructor = new Instructor("Bob", "Doe");
        persons.add(instructor);

        for (Person person1 : persons) {
            System.out.println(person1);
        }
    }
}
