package be.abis.twohelloworld.model;

import java.util.Objects;

public class Instructor extends Person {
    private double salary;

    public Instructor() {}

    public Instructor(String firstName, String lastName, Integer age, String emailAddress, String password, String language, Company company, double salary) {
        super(firstName, lastName, age, emailAddress, password, language, company);
        this.salary = salary;
    }

    public Instructor(String firstName, String lastName, Company company, double salary) {
        super(firstName, lastName, company);
        this.salary = salary;
    }

    public Instructor(String firstName, String lastName, Company company) {
        super(firstName, lastName, company);
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
                "salary=" + salary +
                ", personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", language='" + language + '\'' +
                ", company=" + company +
                '}';
    }

    public void teach(){
        System.out.println(this.firstName + " " + this.lastName + " teaches course");
    }
    public void attendCourse(){
        System.out.println(this.firstName + " " + this.lastName + " attends course");
    }
}
