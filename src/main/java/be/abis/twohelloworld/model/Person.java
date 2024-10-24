package be.abis.twohelloworld.model;

import be.abis.csvmagic.MagicCsvId;
import be.abis.csvmagic.MagicCsvIgnore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Objects;

import static be.abis.twohelloworld.utilities.MyUtillity.getStarDate1970;
import static be.abis.twohelloworld.utilities.MyUtillity.isNullOrEmpty;

public class Person {

    private Integer personId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    @MagicCsvId
    private String emailAddress;
    private String homeAddress;
    private String language;
    private String phone;
    private String mobile;
    private String street;
    private String number;
    private String zipCode;
    private String city;

    public Person() {
    }

    public Person(Integer personId, String firstName, String lastName, LocalDate birthday, String emailAddress, String homeAddress, String language, String phone, String mobile, String street, String number, String zipCode, String city) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.language = language;
        this.phone = phone;
        this.mobile = mobile;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        Double age = getStarDate1970(LocalDate.now()) - getStarDate1970(this.getBirthday());
        return "Person{" +
                "personId=" + personId +
                ", firstName=" + (isNullOrEmpty(firstName)?"":("'" + firstName + "'")) +
                ", lastName=" + (isNullOrEmpty(lastName)?"":("'" + lastName + "'")) +
                ", birthday=" + birthday.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                ", emailAddress=" + (isNullOrEmpty(emailAddress)?"":("'" + emailAddress + "'")) +
                ", homeAddress=" + (isNullOrEmpty(homeAddress)?"":("'" + homeAddress + "'")) +
                ", language=" + (isNullOrEmpty(language)?"":("'" + language + "'")) +
                ", phone=" + (isNullOrEmpty(phone)?"":("'" + phone + "'")) +
                ", mobile=" + (isNullOrEmpty(mobile)?"":("'" + mobile + "'")) +
                ", street=" + (isNullOrEmpty(street)?"":("'" + street + "'")) +
                ", number=" + (isNullOrEmpty(number)?"":("'" + number + "'")) +
                ", zipCode=" + (isNullOrEmpty(zipCode)?"":("'" + zipCode + "'")) +
                ", city=" + (isNullOrEmpty(city)?"":("'" + city + "'")) +
                ", age=" + age +
                '}';
    }
}
