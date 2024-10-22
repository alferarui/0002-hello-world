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

    public void teach(){
        System.out.println(this.getFirstName() + " " + this.getLastName() + " teaches course");
    }

    public void attendCourse(){
        super.attendCourse();
        System.out.println("with extra attention");
    }
}
