package be.abis.twohelloworld.model;

import java.util.Objects;

public class Person {

    public static Integer sequence = 1;

    private Integer personId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String emailAddress;
    private String homeAddress;
    private String password;
    private String language;
    private Company company;

    public Person(){
        this.personId = sequence++;
    }

    public Person(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(Integer personId, String firstName, String lastName, Integer age, String emailAddress, String homeAddress, String password, String language, Company company) {
        this();
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.password = password;
        this.language = language;
        this.company = company;
    }

    public Person(Company company, String language, String password, String homeAddress, String emailAddress, Integer age, String lastName, String firstName) {
        this();
        this.company = company;
        this.language = language;
        this.password = password;
        this.homeAddress = homeAddress;
        this.emailAddress = emailAddress;
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public static Integer getSequence() {
        return sequence;
    }

    public static void setSequence(Integer sequence) {
        Person.sequence = sequence;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personId, person.personId) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(age, person.age) && Objects.equals(emailAddress, person.emailAddress) && Objects.equals(homeAddress, person.homeAddress) && Objects.equals(password, person.password) && Objects.equals(language, person.language) && Objects.equals(company, person.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, age, emailAddress, homeAddress, password, language, company);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + ((personId==null)?"null":(personId)) +
                ", firstName='" + ((firstName==null)?"null":('"' + firstName + '"'))  +
                ", lastName=" + ((lastName==null)?"null":('"' + lastName + '"'))  +
                ", age=" + age +
                ", emailAddress=" + ((emailAddress==null)?"null":('"' + emailAddress + '"'))  +
                ", homeAddress=" + ((homeAddress==null)?"null":('"' + homeAddress + '"'))  +
                ", password=" + ((password==null)?"null":('"' + password + '"'))  +
                ", language=" + ((language==null)?"null":('"' + language + '"'))  +
                ", company=" + company +
                '}';
    }

    public void attendCourse(){
        System.out.println(this.firstName + " " + this.lastName + " attends course");
    }
}
