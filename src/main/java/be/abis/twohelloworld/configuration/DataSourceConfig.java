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
        return DataSourceBuilder.create()
                .url("jdbc:oracle:thin:@//localhost:1521/orcl")
                .username("oracle_user")
                .password("oracle_password")
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
