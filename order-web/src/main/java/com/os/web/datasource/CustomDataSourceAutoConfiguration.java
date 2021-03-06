package com.os.web.datasource;

import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@EnableConfigurationProperties({
        CustomDataSourceAutoConfiguration.SecondDSProperties.class,
        CustomDataSourceAutoConfiguration.DerbyDSProperties.class
})
public class CustomDataSourceAutoConfiguration {
    @Autowired
    private SecondDSProperties secondDSProperties;

    @Autowired
    private DerbyDSProperties derbyDSProperties;

    @Value("${derby.system.home}")
    private String derbyHome;

    @Bean(name="secondDataSource")
    @Qualifier("secondDS")
    public DataSource secondDataSource(){
        EmbeddedConnectionPoolDataSource pooled = new EmbeddedConnectionPoolDataSource();
        pooled.setUser(secondDSProperties.getUsername());
        pooled.setPassword(secondDSProperties.getPassword());
        pooled.setDatabaseName(secondDSProperties.getDbname());

        File rootDir = new File(System.getProperty("user.dir")+File.separator+derbyHome);
        File databaseDir = new File(rootDir,secondDSProperties.getDbname());
        if(!databaseDir.exists()){
            pooled.setCreateDatabase("create");
        }
        return pooled;
    }

    @Primary
    @Bean(name="primaryDataSource")
    @Qualifier("primaryDS")
    public DataSource derbyDataSource(){
        EmbeddedConnectionPoolDataSource pooled = new EmbeddedConnectionPoolDataSource();
        pooled.setUser(derbyDSProperties.getUsername());
        pooled.setPassword(derbyDSProperties.getPassword());
        pooled.setDatabaseName(derbyDSProperties.getDbname());

        File rootDir = new File(System.getProperty("user.dir")+File.separator+derbyHome);
        File databaseDir = new File(rootDir,derbyDSProperties.getDbname());
        if(!databaseDir.exists()){
            pooled.setCreateDatabase("create");
        }
        return pooled;
    }

    private DataSource crateDataSource(BaseDataSourceProperties properties){
        DataSourceBuilder builder = DataSourceBuilder.create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());
        return builder.build();
    }

    @Bean(name="primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDS") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="originalJdbcTemplate")
    public JdbcTemplate originalJdbcTemplate(@Qualifier("secondDS") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @ConfigurationProperties(prefix = "spring.datasource.secondDB")
    static class SecondDSProperties extends BaseDataSourceProperties{}

    @ConfigurationProperties(prefix = "spring.datasource.primary")
    static class DerbyDSProperties extends BaseDataSourceProperties{}

    static class BaseDataSourceProperties{
        private String url;

        private String driverClassName;

        private String username;

        private String password;

        private String dbname;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDbname() {
            return dbname;
        }

        public void setDbname(String dbname) {
            this.dbname = dbname;
        }
    }
}
