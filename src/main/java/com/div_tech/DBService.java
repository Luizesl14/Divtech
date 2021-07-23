package com.div_tech;

import ch.qos.logback.core.db.DriverManagerConnectionSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration
public class DBService {

    public DBService(){
    }

    // mysql://b82cfabc16a20a:e70c248d@us-cdbr-east-04.cleardb.com/heroku_c5c6e6eddd9e519?reconnect=true

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_c5c6e6eddd9e519");
        dataSource.setUsername("b82cfabc16a20a");
        dataSource.setPassword("e70c248d");
        return dataSource;
    }




    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }



}
