package be.abis.twohelloworld.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    private final Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    // Method to get the current active profile
    private String getActiveProfile() {
        String[] activeProfiles = env.getActiveProfiles();
        // Return the first active profile if it exists, or "default" if none is set
        return activeProfiles.length > 0 ? activeProfiles[0] : "default";
    }

    @Value("${spring.datasource.oracle.url}")
    private String oracleUrl;

    @Value("${spring.datasource.oracle.username}")
    private String oracleUsername;

    @Value("${spring.datasource.oracle.password}")
    private String oraclePassword;

    @Value("${spring.datasource.oracle.driver-class-name}")
    private String oracleDriverClassName;

    @Bean(name = "oracleDataSource")
    public DataSource oracleDataSource() {
        System.out.println("oracleDataSource :: Active Profile: " + getActiveProfile()); // For debugging or logging

        /** TODO make it retrieve
         * spring.datasource.oracle.url
         * spring.datasource.oracle.username
         * spring.datasource.oracle.password
         * spring.datasource.oracle.driver
         * from application.properties
         * */
        return DataSourceBuilder.create()
                .url(oracleUrl)
                .username(oracleUsername)
                .password(oraclePassword)
                .driverClassName(oracleDriverClassName)
                .build();
    }


    @Value("${spring.datasource.sqlite.url}")
    private String sqliteUrl;

    @Value("${spring.datasource.sqlite.driver-class-name}")
    private String sqliteDriverClassName;

    // SQLite DataSource
    @Bean(name = "sqliteDataSource")
    public DataSource sqliteDataSource() {
        System.out.println("sqliteDataSource :: Active Profile: " + getActiveProfile()); // For debugging or logging

        return DataSourceBuilder.create()
                .url(sqliteUrl)
                .driverClassName(sqliteDriverClassName)
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
