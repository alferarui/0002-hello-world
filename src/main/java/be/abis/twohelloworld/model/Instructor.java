package be.abis.twohelloworld.model;

import java.time.LocalDate;
import java.util.Objects;

public class Instructor extends Person {
    private double salary;

    public Instructor() {
        super();
    }

    public Instructor(Integer personId, String firstName, String lastName, LocalDate birthday, String emailAddress, String homeAddress, String password, String language, Company company, double salary) {
        super(personId, firstName, lastName, birthday, emailAddress, homeAddress, password, language, company);
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
        return "Instructor{" +
                "personId=" + ((getPersonId() ==null)?"null":(getPersonId())) +
                ", firstName='" + ((getFirstName() ==null)?"null":('"' + getFirstName() + '"'))  +
                ", lastName=" + ((getLastName() ==null)?"null":('"' + getLastName() + '"'))  +
                ", age=" + getBirthday() +
                ", emailAddress=" + ((getEmailAddress() ==null)?"null":('"' + getEmailAddress() + '"'))  +
                ", homeAddress=" + ((getHomeAddress() ==null)?"null":('"' + getHomeAddress() + '"'))  +
                ", password=" + ((getPassword() ==null)?"null":('"' + getPassword() + '"'))  +
                ", language=" + ((getLanguage() ==null)?"null":('"' + getLanguage() + '"'))  +
                ", company=" + getCompany() +
                "salary=" + salary +
                '}';
    }
}
