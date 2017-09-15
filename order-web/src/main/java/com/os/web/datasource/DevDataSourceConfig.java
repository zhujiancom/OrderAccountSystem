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
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({
        DevDataSourceConfig.Derby2Properties.class,
        DevDataSourceConfig.DerbyDSProperties.class,
        DevDataSourceConfig.MysqlDSProperties.class
})
@Profile("dev")
public class DevDataSourceConfig {
    @Autowired
    private Derby2Properties derby2DSProperties;

    @Autowired
    private DerbyDSProperties derbyDSProperties;

    @Autowired
    private MysqlDSProperties mysqlDSProperties;

    @Value("${derby.system.home}")
    private String derbyHome;

    @Bean(name="derby1DataSource")
    @Qualifier("derby1DS")
    public DataSource derbyDataSource(){
        EmbeddedConnectionPoolDataSource pooled = new EmbeddedConnectionPoolDataSource();
        pooled.setUser(derbyDSProperties.getUsername());
        pooled.setPassword(derbyDSProperties.getPassword());
        pooled.setDatabaseName("memory:OrderWebMemDB");
        pooled.setCreateDatabase("create");
        pooled.setDataSourceName(derbyDSProperties.getUsername());

        Resource initSchema =  new ClassPathResource("scripts/schema-derby.sql");
        Resource initData =  new ClassPathResource("scripts/data-derby.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema,initData);
        DatabasePopulatorUtils.execute(databasePopulator,pooled);
        return pooled;
    }

    @Bean(name="derby2DataSource")
    @Qualifier("derby2DS")
    public DataSource derby2DataSource(){
        EmbeddedConnectionPoolDataSource pooled = new EmbeddedConnectionPoolDataSource();
        pooled.setUser(derby2DSProperties.getUsername());
        pooled.setPassword(derby2DSProperties.getPassword());
        pooled.setDatabaseName(derby2DSProperties.getDbname());
        return pooled;
    }

    @Primary
    @Bean(name="mysqlDataSource")
    @Qualifier("mysqlDS")
    public DataSource mysqlDataSource(){
        DataSource dataSource = crateDataSource(mysqlDSProperties);
//        Resource initSchema =  new ClassPathResource("scripts/schema-mysql.sql");
//        Resource initData =  new ClassPathResource("scripts/data-mysql.sql");
//        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema,initData);
//        DatabasePopulatorUtils.execute(databasePopulator,dataSource);
        return dataSource;
    }

    private DataSource crateDataSource(BaseDataSourceProperties properties){
        DataSourceBuilder builder = DataSourceBuilder.create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());
        return builder.build();
    }

    @Bean(name="derby1JdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("derby1DS") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="derby2JdbcTemplate")
    public JdbcTemplate originalJdbcTemplate(@Qualifier("derby2DS") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDS") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @ConfigurationProperties(prefix = "spring.datasource.derby.2")
    static class Derby2Properties extends BaseDataSourceProperties{}

    @ConfigurationProperties(prefix = "spring.datasource.derby.1")
    static class DerbyDSProperties extends BaseDataSourceProperties{}

    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    static class MysqlDSProperties extends BaseDataSourceProperties{}

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
