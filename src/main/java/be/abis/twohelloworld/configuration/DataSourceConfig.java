package be.abis.twohelloworld.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    // Oracle DataSource
    @Bean(name = "oracleDataSource")
    public DataSource oracleDataSource() {
        /** TODO make it retrieve
         * spring.datasource.oracle.url
         * spring.datasource.oracle.username
         * spring.datasource.oracle.password
         * spring.datasource.oracle.driver
         * from application.properties
         * */
        return DataSourceBuilder.create()
                .url("jdbc:oracle:thin:@//delphi.abis.be:1521/TSTA")
                .username("tu00057")
                .password("tu00057")
                .driverClassName("oracle.jdbc.OracleDriver")
                .build();
    }

    // SQLite DataSource
    @Bean(name = "sqliteDataSource")
    public DataSource sqliteDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:sqlite:sqlite_database.db")
                .driverClassName("org.sqlite.JDBC")
                .build();
    }

    // Oracle JdbcTemplate
    @Bean(name = "oracleJdbcTemplate")
    public JdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // SQLite JdbcTemplate
    @Bean(name = "sqliteJdbcTemplate")
    public JdbcTemplate sqliteJdbcTemplate(@Qualifier("sqliteDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
