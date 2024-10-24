package be.abis.twohelloworld.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static be.abis.twohelloworld.utilities.MyUtillity.getStarDate1970;
import static be.abis.twohelloworld.utilities.MyUtillity.isNullOrEmpty;

public class Instructor extends Person {
    private double salary;

    public Instructor() {
        super();
    }

    public Instructor(Integer personId, String firstName, String lastName, LocalDate birthday, String emailAddress, String homeAddress, String language, String phone, String mobile, String street, String number, String zipCode, String city, double salary) {
        super(personId, firstName, lastName, birthday, emailAddress, homeAddress, language, phone, mobile, street, number, zipCode, city);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Instructor that = (Instructor) o;
        return Double.compare(salary, that.salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }


    @Override
    public String toString() {
        double age = getStarDate1970(LocalDate.now()) - getStarDate1970(this.getBirthday());
        return "Person{" +
                "personId=" + getPersonId() +
                ", firstName='" + (isNullOrEmpty(getFirstName())?"":("'" + getFirstName() + "'")) + '\'' +
                ", lastName='" + (isNullOrEmpty(getLastName())?"":("'" + getLastName() + "'")) + '\'' +
                ", birthday=" + getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE) +
                ", emailAddress='" + (isNullOrEmpty(getEmailAddress())?"":("'" + getEmailAddress() + "'")) + '\'' +
                ", homeAddress='" + (isNullOrEmpty(getHomeAddress())?"":("'" + getHomeAddress() + "'")) + '\'' +
                ", language='" + (isNullOrEmpty(getLanguage())?"":("'" + getLanguage() + "'")) + '\'' +
                ", phone='" + (isNullOrEmpty(getPhone())?"":("'" + getPhone() + "'")) + '\'' +
                ", mobile='" + (isNullOrEmpty(getMobile())?"":("'" + getMobile() + "'")) + '\'' +
                ", street='" + (isNullOrEmpty(getStreet())?"":("'" + getStreet() + "'")) + '\'' +
                ", number='" + (isNullOrEmpty(getNumber())?"":("'" + getNumber() + "'")) + '\'' +
                ", zipCode='" + (isNullOrEmpty(getZipCode())?"":("'" + getZipCode() + "'")) + '\'' +
                ", city='" + (isNullOrEmpty(getCity())?"":("'" + getCity() + "'")) + '\'' +
                ", salary='" + getSalary() +
                ", age=" + age +
                '}';
    }
}
