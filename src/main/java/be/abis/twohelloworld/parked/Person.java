package be.abis.twohelloworld.parked;

import be.abis.csvmagic.MagicCsvId;
import be.abis.csvmagic.MagicCsvIgnore;
import be.abis.twohelloworld.model.Company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Objects;

public class Person {

    private Integer personId;
    @MagicCsvId
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String emailAddress;
    private String homeAddress;
    private String password;
    private String language;
    @MagicCsvIgnore
    private Company company;

    public Person(){
    }

    public Person(Integer personId, String firstName, String lastName, LocalDate birthday, String emailAddress, String homeAddress, String password, String language, Company company) {
        this();
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.password = password;
        this.language = language;
        this.company = company;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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
        return Objects.equals(personId, person.personId)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(birthday, person.birthday)
                && Objects.equals(emailAddress, person.emailAddress)
                && Objects.equals(homeAddress, person.homeAddress)
                && Objects.equals(password, person.password) &&
                Objects.equals(language, person.language) &&
                Objects.equals(company, person.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, birthday, emailAddress, homeAddress, password, language, company);
    }

    public static Double getStarDate1970(LocalDate ld){
        int currentYear = ld.getYear();
        int dayOfYear = ld.getDayOfYear();
        int totalDaysInYear = ld.lengthOfYear();
        double dayOfYearFraction = (double) dayOfYear / totalDaysInYear;
        return (currentYear - 1970) + (dayOfYearFraction);
    }
    @Override
    public String toString() {

        Double age = getStarDate1970(LocalDate.now()) - getStarDate1970(this.getBirthday());
        return "Person{" +
                "personId=" + ((personId==null)?"null":(personId)) +
                ", firstName=" + ((firstName==null)?"null":('"' + firstName + '"'))  +
                ", lastName=" + ((lastName==null)?"null":('"' + lastName + '"'))  +
                ", birthday=" + birthday.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                ", age=" + age +
                ", emailAddress=" + ((emailAddress==null)?"null":('"' + emailAddress + '"'))  +
                ", homeAddress=" + ((homeAddress==null)?"null":('"' + homeAddress + '"'))  +
                ", password=" + ((password==null)?"null":('"' + password + '"'))  +
                ", language=" + ((language==null)?"null":('"' + language + '"'))  +
                ", company=" + company +
                '}';
    }
}
