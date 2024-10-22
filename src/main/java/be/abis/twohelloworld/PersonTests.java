package be.abis.twohelloworld;

import be.abis.twohelloworld.model.Address;
import be.abis.twohelloworld.model.Company;
import be.abis.twohelloworld.model.Person;

public class PersonTests {
    public static void main(String[] args) {

        Person person = new Person(){{
            Person p = this;
            p.setFirstName("John");
            p.setLastName("Doe");
            p.setAge(35);
            p.setPersonId(1);
            p.setEmailAddress("john.doe@abis.be");
            p.setPassword("34rFwe4£$R3ddr!");
            p.setLanguage("en-INT");

            setCompany(new Company(){{
                Company company=this;
                company.setName("Abis");
                company.setTelephoneNumber("+32472123456");
                company.setVatNr("4234535348574384579");
                setAddress(new Address(){{
                    Address address=this;
                    address.setTown("Leuven");
                    address.setZipCode("3000");
                    address.setNr("23");
                    address.setStreet("Brusselsesteenweg");
                }});
            }});

        }};

        System.out.println(person.toString());

        System.out.println(
                "\"" + person.getFirstName() + " " + person.getLastName()
                        + " is " + person.getAge() + " old and works for "
                        + person.getCompany().getName()+" in " + person.getCompany().getAddress().getTown()
                        + "`"
        );
    }
}
