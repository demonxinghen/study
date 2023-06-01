目录结构,顶层package为com.example
```text
--java
  --controller
  --service
  --configuration
    --MysqlConfiguration.java
    --OracleConfiguration.java
  --mapper
    --mysql
      --MysqlMapper.java
    --oracle
      --OracleMapper.java
--resources
  --mapper
    --mysql
      --MysqlMapper.xml
    --oracle
      --OracleMapper.xml
```
application.yml
```yaml
spring:
  datasource:
    mysql:
      jdbc-url: jdbc:mysql://127.0.0.1:3306/test
      username: test
      password: test
      driver-class-name: com.mysql.cj.jdbc.Driver
    oracle:
      jdbc-url: jdbc:oracle:thin:@127.0.0.1:1521:test
      username: test
      password: test
      driver-class-name: oracle.jdbc.driver.OracleDriver
```
MysqlConfiguration.java

```java
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// sqlSessionFactoryRef不能少,否则会提示重复
@MapperScan(basePackages = "com.example.mapper.mysql", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlConfiguration {

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("mysqlSqlSessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 不能少,否则会报错,找不到对应的sql
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/mysql/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
```
MysqlMapper.java
```java
// Configuration使用了@MapperScan,指定目录下的mapper就不需要使用@Mapper
public class MysqlMapper{
    
}
```
OracleConfiguration.java
```java
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// sqlSessionFactoryRef不能少,否则会提示重复
@MapperScan(basePackages = "com.example.mapper.oracle", sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class MysqlConfiguration {

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("oracleSqlSessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 不能少,否则会报错,找不到对应的sql
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/oracle/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
```
OracleMapper.java
```java
public class OracleMapper{
    
}
```