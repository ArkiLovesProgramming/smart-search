package arki.smartsearch.sssearchserver.conf;


import arki.smartsearch.sssearchserver.utility.AWSUtility;
import com.alibaba.fastjson.JSON;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @description: This is for dynamically retrieving database on AWS RDS password.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceConfig {
    private String driverClassName;
    private String username;
    private String url;
    private String password;

    @Bean
    @Primary
    public DataSource dataSource (){
        HikariDataSource dataSource = new HikariDataSource();
        Map map = JSON.parseObject(AWSUtility.getSecret(), Map.class);
        dataSource.setPassword((String) map.get("password"));
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername((String) map.get("username"));
        dataSource.setJdbcUrl(url);
        return dataSource;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
