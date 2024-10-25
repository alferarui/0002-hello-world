package be.abis.twohelloworld.repository.sqlite;

import be.abis.twohelloworld.model.Person;
import be.abis.twohelloworld.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public class PersonRepositorySqlite implements PersonRepository {

    private final JdbcTemplate sqliteJdbcTemplate;
    // RowMapper to convert ResultSet rows into person objects
    public final RowMapper<Person> personsqliteRowMapper = (rs, rowNum) -> {
        Person person = new Person();

        person.setPersonId(rs.getInt("PERSON_ID"));
        person.setFirstName(rs.getString("FIRST_NAME"));
        person.setLastName(rs.getString("LAST_NAME"));
        person.setBirthday(rs.getDate("BIRTHDAY").toLocalDate());
        person.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
        person.setHomeAddress(rs.getString("HOME_ADDRESS"));
        person.setLanguage(rs.getString("LANGUAGE"));
        person.setPhone(rs.getString("PHONE"));
        person.setMobile(rs.getString("MOBILE"));
        person.setStreet(rs.getString("STREET"));
        person.setNumber(rs.getString("NUMBER"));
        person.setZipCode(rs.getString("ZIP_CODE"));
        person.setCity(rs.getString("CITY"));

        return person;
    };

    public PersonRepositorySqlite(
            @Qualifier("sqliteJdbcTemplate") JdbcTemplate sqliteJdbcTemplate
    ) {
        this.sqliteJdbcTemplate = sqliteJdbcTemplate;
    }

    @Override
    public void add(Person ent) {

    }

    @Override
    public void remove(Person ent) {

    }

    @Override
    public void update(Person ent) {

    }

    @Override
    public List<Person> find(Predicate<? super Person> predicate) {

        String sql="SELECT" +
                "    PERSON_ID," +
                "    FIRST_NAME," +
                "    LAST_NAME," +
                "    BIRTHDAY," +
                "    EMAIL_ADDRESS," +
                "    HOME_ADDRESS," +
                "    LANGUAGE," +
                "    PHONE," +
                "    MOBILE," +
                "    STREET," +
                "    NUMBER" +
                "    ZIP_CODE" +
                "FROM person";
        PersonRepositorySqlite self=this;
        return sqliteJdbcTemplate.query(sql, personsqliteRowMapper).stream().filter(predicate).toList();
    }

    @Override
    public List<Person> match(String regexpString) {
        String sql="SELECT" +
                "    PERSON_ID," +
                "    FIRST_NAME," +
                "    LAST_NAME," +
                "    BIRTHDAY," +
                "    EMAIL_ADDRESS," +
                "    HOME_ADDRESS," +
                "    LANGUAGE," +
                "    PHONE," +
                "    MOBILE," +
                "    STREET," +
                "    NUMBER" +
                "    ZIP_CODE" +
                "FROM person" +
                "WHERE " +
                "    REGEXP_LIKE(" +
                "        FIRST_NAME||'$'||" +
                "        LAST_NAME||'$'||" +
                "        EMAIL_ADDRESS||'$'||" +
                "        HOME_ADDRESS||'$'||" +
                "        LANGUAGE||'$'||" +
                "        PHONE||'$'||" +
                "        MOBILE||'$'||" +
                "        STREET,'"+regexpString+"')";
        return sqliteJdbcTemplate.query(sql, personsqliteRowMapper);
    }

    @Override
    public int count() {
        String sql="SELECT count(*) as CNT FROM PERSON";
        return sqliteJdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("CNT")).get(0);
    }

    @Override
    public void clear() {
        String sql="DELETE FROM PERSON WHERE 1=1";
        sqliteJdbcTemplate.update(sql);
    }

    @Override
    public List<Person> all() {
        String sql="SELECT" +
            "    PERSON_ID," +
            "    FIRST_NAME," +
            "    LAST_NAME," +
            "    BIRTHDAY," +
            "    EMAIL_ADDRESS," +
            "    HOME_ADDRESS," +
            "    LANGUAGE," +
            "    PHONE," +
            "    MOBILE," +
            "    STREET," +
            "    NUMBER" +
            "    ZIP_CODE" +
            "FROM person";
        return sqliteJdbcTemplate.query(sql, personsqliteRowMapper);
    }
}
