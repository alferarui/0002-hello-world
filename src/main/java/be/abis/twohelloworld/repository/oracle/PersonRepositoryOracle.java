package be.abis.twohelloworld.repository.oracle;

import be.abis.twohelloworld.model.Person;
import be.abis.twohelloworld.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

import static be.abis.twohelloworld.repository.Jdbcutils.personRowMapper;

@Repository
public class PersonRepositoryOracle implements PersonRepository {

    private final JdbcTemplate oracleJdbcTemplate;

    public PersonRepositoryOracle(
            @Qualifier("sqliteJdbcTemplate") JdbcTemplate oracleJdbcTemplate
    ) {
        this.oracleJdbcTemplate = oracleJdbcTemplate;
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

        String sql="""
            SELECT
              p.PNO,
              p.PLNAME,
              p.PFNAME,
              p.PFUNC,
              p.PA_CONO,
              p.PADEPT,
              p.PTEL,
              p.PSEX
            FROM ABISPERSONS p
        """;
        return oracleJdbcTemplate.query(sql, personRowMapper).stream().filter(predicate).toList();
    }

    @Override
    public List<Person> match(String regexpString) {
        String sql="""
            SELECT
              PNO,
              PLNAME,
              PFNAME,
              PFUNC,
              PA_CONO,
              PADEPT,
              PTEL,
              PSEX
            FROM ABISPERSONS
            WHERE regexp_like(P_FULL_INFO,'?')
        """;
        return oracleJdbcTemplate.query(sql, personRowMapper,regexpString);
    }

    @Override
    public int count() {
        String sql="SELECT count(*) as CNT FROM ABISPERSONS";
        return oracleJdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("CNT")).get(0);
    }

    @Override
    public void clear() {
        String sql="DELETE FROM ABISPERSONS WHERE 1=1";
        oracleJdbcTemplate.update(sql);
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
        return oracleJdbcTemplate.query(sql, personRowMapper);
    }
}
