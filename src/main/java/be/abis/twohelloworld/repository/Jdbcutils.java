package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.model.Person;
import org.springframework.jdbc.core.RowMapper;

public class Jdbcutils {
    public static RowMapper<Course> courseRowMapper=(rs, rowNum) -> {
        Course course = new Course();
        course.setCourseId(rs.getInt("CID"));
        course.setShortTitle(rs.getString("CSTITLE").trim());
        course.setLongTitle(rs.getString("CLTITLE").trim());
        course.setNumberOfDays(rs.getInt("CDUR"));
        course.setPricePerDay(rs.getInt("CAPRICE"));

        return course;
    };


    // RowMapper to convert ResultSet rows into person objects
    public static final RowMapper<Person> personRowMapper = (rs, rowNum) -> {
        Person person = new Person();

        person.setPersonId(rs.getInt("PERSON_ID"));
        person.setFirstName(rs.getString("FIRST_NAME").trim());
        person.setLastName(rs.getString("LAST_NAME").trim());
        person.setBirthday(rs.getDate("BIRTHDAY").toLocalDate());
        person.setEmailAddress(rs.getString("EMAIL_ADDRESS").trim());
        person.setHomeAddress(rs.getString("HOME_ADDRESS").trim());
        person.setLanguage(rs.getString("LANGUAGE").trim());
        person.setPhone(rs.getString("PHONE").trim());
        person.setMobile(rs.getString("MOBILE").trim());
        person.setStreet(rs.getString("STREET").trim());
        person.setNumber(rs.getString("NUMBER").trim());
        person.setZipCode(rs.getString("ZIP_CODE").trim());
        person.setCity(rs.getString("CITY").trim());

        return person;
    };
}
