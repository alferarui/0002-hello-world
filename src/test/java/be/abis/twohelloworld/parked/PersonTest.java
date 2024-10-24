package be.abis.twohelloworld.parked;

import be.abis.twohelloworld.model.Address;
import be.abis.twohelloworld.model.Company;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static be.abis.twohelloworld.utilities.MyUtillity.getStarDate1970;
import static java.lang.Math.floor;

class PersonTest {

    @Test
    void testToString() {
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

        Person person = new Person(){{
            Person p = this;
            p.setFirstName("John");
            p.setLastName("Doe");
            p.setBirthday(LocalDate.parse("1988-10-01", DateTimeFormatter.ISO_DATE));
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

        double age = getStarDate1970(LocalDate.now()) - getStarDate1970(person.getBirthday());
        System.out.println(
                "\"" + person.getFirstName() + " " + person.getLastName()
                + " is " + ((int)floor(age)) + " year" + (age>=2?'s':"")+ " old and works for "
                + person.getCompany().getName()+" in " + person.getCompany().getAddress().getTown()
                + "`"
        );

    }

    @Test
    void getStarTrekIntStarDateTest() {
        Double a = getStarDate1970(LocalDate.now());
        Double b = getStarDate1970(LocalDate.parse("1999-10-01", DateTimeFormatter.ISO_DATE));
        Double c = getStarDate1970(LocalDate.parse("1978-10-01", DateTimeFormatter.ISO_DATE));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(a-b);
        System.out.println(a-c);
    }
}