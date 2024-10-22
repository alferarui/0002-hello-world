package be.abis.twohelloworld.model;

import java.util.Objects;

public class Instructor extends Person {
    private double salary;

    public Instructor() {
        super();
    }

    public Instructor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "personId=" + ((getPersonId() ==null)?"null":(getPersonId())) +
                ", firstName='" + ((getFirstName() ==null)?"null":('"' + getFirstName() + '"'))  +
                ", lastName=" + ((getLastName() ==null)?"null":('"' + getLastName() + '"'))  +
                ", age=" + getAge() +
                ", emailAddress=" + ((getEmailAddress() ==null)?"null":('"' + getEmailAddress() + '"'))  +
                ", homeAddress=" + ((getHomeAddress() ==null)?"null":('"' + getHomeAddress() + '"'))  +
                ", password=" + ((getPassword() ==null)?"null":('"' + getPassword() + '"'))  +
                ", language=" + ((getLanguage() ==null)?"null":('"' + getLanguage() + '"'))  +
                ", company=" + getCompany() +
                "salary=" + salary +
                '}';
    }

    public void teach(){
        System.out.println(this.getFirstName() + " " + this.getLastName() + " teaches course");
    }

    @Override
    public void attendCourse(){
        super.attendCourse();
        System.out.println("with extra attention");
    }
}
