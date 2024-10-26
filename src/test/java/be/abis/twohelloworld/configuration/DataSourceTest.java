package be.abis.twohelloworld.configuration;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.repository.Jdbcutils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static be.abis.twohelloworld.repository.Jdbcutils.courseRowMapper;

@SpringBootTest
public class DataSourceTest {

    public class KeyValue{
        String nam;
        String val;

        public String getNam() {
            return nam;
        }

        public void setNam(String nam) {
            this.nam = nam;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "KeyValue{" +
                    "nam='" + nam + '\'' +
                    ", val='" + val + '\'' +
                    '}';
        }
    }
    public RowMapper<KeyValue> mapper = (rs,i) -> {
        KeyValue kv = new KeyValue();
        kv.setNam(rs.getString("nam"));
        kv.setVal(rs.getString("val"));
        return kv;
    };
    @Autowired
    @Qualifier("sqliteJdbcTemplate")
    JdbcTemplate sqlite;


    @Autowired
    @Qualifier("oracleJdbcTemplate")
    JdbcTemplate oracle;

    @Test
    public void testSqliteConnectionViaDataSource() {
        try (Connection c = Objects.requireNonNull(sqlite.getDataSource()).getConnection()) {
            System.out.println("Connection succeeded via "
                    + c.getMetaData().getDatabaseProductName() + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testOracleConnectionViaDataSource() {
        try (Connection c = Objects.requireNonNull(oracle.getDataSource()).getConnection()) {
            System.out.println("Connection succeeded via "
                    + c.getMetaData().getDatabaseProductName() + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testQueriesOnSqlite(){
        String sql="""
                select 1 as val, 'hello' as nam
                UNION ALL 
                select 2 as val, 'world' as nam
                UNION ALL 
                select 3 as val, '!' as nam
        """;
        List<KeyValue> kvResult = sqlite.query(sql,mapper);
        for(KeyValue kv : kvResult) {
            System.out.println(kv);
        }
    }
    @Test
    public void testQueriesOnOracle(){
        String sql="""
                select 1 as val, 'hello' as nam from DUAL 
                UNION ALL 
                select 2 as val, 'world' as nam from DUAL 
                UNION ALL 
                select 3 as val, '!' as nam from DUAL
        """;
        List<KeyValue> kvResult = oracle.query(sql,mapper);
        for(KeyValue kv : kvResult) {
            System.out.println(kv);
        }
    }

    @Test
    public void testCoursesOnSqlite(){
        String sql="""
        SELECT
            CID,
            CSTITLE,
            CLTITLE,
            CDUR,
            CAPRICE
        FROM ABISCOURSES
        """;
        List<Course> kvResult = sqlite.query(sql, courseRowMapper);
        for(Course kv : kvResult) {
            System.out.println(kv);
        }
    }
    @Test
    public void testCoursesOnOracle(){
        String sql="""
        SELECT
            CID,
            CSTITLE,
            CLTITLE,
            CDUR,
            CAPRICE
        FROM ABISCOURSES
        """;
        List<Course> kvResult = oracle.query(sql,courseRowMapper);
        for(Course kv : kvResult) {
            System.out.println(kv);
        }
    }
}
